import java.io.*;

public class MemoryManager {
    public static final int BLOCK_SIZE = 512;
    public static final int TOTAL_MEMORY = 1024 * 1024; // 1 Mo
    public static final int NUM_BLOCKS = TOTAL_MEMORY / BLOCK_SIZE; // 2048

    // Offsets des différentes zones
    public static final int SUPERBLOCK_OFFSET = 0;
    public static final int BITMAP_OFFSET = BLOCK_SIZE;
    public static final int INODE_TABLE_OFFSET = 2 * BLOCK_SIZE;
    public static final int DATA_OFFSET = 129 * BLOCK_SIZE;

    public static final int INODE_SIZE = 128; // Taille d'un inode en octets
    public static final int INODE_TABLE_SIZE = DATA_OFFSET - INODE_TABLE_OFFSET;
    public static final int MAX_INODES = INODE_TABLE_SIZE / INODE_SIZE; // => 508

    // CONTRAINTE : Un seul tableau pour TOUT le système de fichiers
    private byte[] filesystemMemory;

    public MemoryManager() {
        this.filesystemMemory = new byte[TOTAL_MEMORY];
        initializeFilesystem();
    }

    private void initializeFilesystem() {
        // Initialiser le superbloc
        writeSuperblock();

        // Marquer les blocs système comme occupés
        setBlockUsed(0);  // superbloc
        setBlockUsed(1);  // bitmap

        for (int i = 2; i < 129; i++)
            setBlockUsed(i);  // table des inodes
    }

    private void writeSuperblock() {

        // Exemple minimal (tu peux stocker plus d’infos si tu veux)
        String signature = "MYFS1.0";

        //! correction
        // ÉTAPE 1: Écrire la signature du système
        for (int i = 0; i < Math.min(27, data.length); i++) {
                memory[i] = (byte) signature.charAt(i);
        }

        // byte [] data = signature.getBytes()
        // equivalent System.arraycopy(data, 0, memory, 0, Math.min(27, data.length));

        // ÉTAPE 2: Écrire quelques infos importantes à des positions fixes
        Utils.writeInt(memory, 16, BLOCK_SIZE);     // Position 16: taille des blocs
        Utils.writeInt(memory, 20, TOTAL_MEMORY);   // Position 20: taille totale
        Utils.writeInt(memory, 24, NUM_BLOCKS);     // Position 24: nombre de blocs
    }

    public boolean setBlockUsed(int blockNumber, boolean used) {
        if (blockNumber < 0 || blockNumber >= NUM_BLOCKS)
            return false;

        int byteIndex = blockNumber / 8;
        int bitPosition = blockNumber % 8;
        int offset = BITMAP_OFFSET + byteIndex;

        // Complétez cette partie !
        // INDICE: Utilisez les opérations | (OR) et & (AND) avec des masques

        if (used) {
            // Mettre le bit à 1 (bloc occupé)
            memory[offset] |= 1 << bitPosition;
        } else {
            // Mettre le bit à 0 (bloc libre)
            memory[offset] &= ~(1 << bitPosition);
        }

        return true;
    }

    public int isBlockUsed(int blockNumber) {
        if (blockNumber < 0 || blockNumber >= NUM_BLOCKS)
                    return -1;

        int byteIndex = blockNumber / 8;
        int bitPosition = blockNumber % 8;
        int offset = BITMAP_OFFSET + byteIndex;

        return (filesystemMemory[offset] >> bitPosition) & 1;
    }

    public int allocateBlock() {
        //Complétez cette méthode étape par étape
        // ÉTAPE 1: Boucle de la page 129 à la fin (les pages de données)
        // ÉTAPE 2: Pour chaque page, vérifier si elle est libre
        // ÉTAPE 3: Si libre, la marquer comme occupée
        // ÉTAPE 4: Retourner son numéro
        // AIDE: Commencer par cette structure
        for (int i = 129; i < NUM_BLOCKS; i++) {
            if (isBlockUsed(i) == 0) {  // Bloc libre trouvé !
                // Te marquer comme occupé
                setBlockUsed(i,true);
                /L'annoncer à l'utilisateur
                System.out.println("Le bloc" + i + " a bien été marqué comme occupé");
                //Le retourner
                return i;
            }
        }
        return -1; // Pas de bloc libre
    }

    public byte[] getFilesystemMemory() {
        return filesystemMemory;
    }

    public void saveToFile() throws IOException {
        // Complété la sauvegarde du system avec FileOutputStream

        //! correction
        FileOutputStream fos = new FileOutputStream("filesystem.img");
        fos.write(memory);
        fos.close();
    }

    public void loadFromFile() throws IOException {
            // Complété la sauvegarde du system avec FileInputStream
        // AIDE: Utilisez FileInputStream
    }
}
