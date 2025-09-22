import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Image {
    private int width;
    private int height;
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[height][width][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
     */
    public void save_txt(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);

        writer.write("P3\n");
        writer.write(width + " " + height + "\n");
        writer.write("255\n");

        for (int[][] y : pixels) {
            for (int[] x : y) {
                writer.write(x[0] + " " + x[1] + " " + x[2] + " ");
            }
            writer.write("\n");
        }

        writer.close();
    }

    /**
     * Modifier une image au format texte PPM (P3)
     */
    static public Image read_txt(String filename) throws IOException {
        // Lecture du fichier et découpage en tokens
        byte[] data = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filename));
        String txt = new String(data, java.nio.charset.StandardCharsets.UTF_8);
        String[] tokens = txt.split("\\s+");
        if (!tokens[0].equals("P3")) {
            throw new IOException("Format de fichier non supporté : " + tokens[0]);
        }
        if (tokens.length < 4) {
            throw new IOException("Fichier incomplet");
        }
        int width = Integer.parseInt(tokens[1]);
        int height = Integer.parseInt(tokens[2]);
        int max = Integer.parseInt(tokens[3]);
        Image img = new Image(width, height);
        int idx = 4;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = Integer.parseInt(tokens[idx++]);
                int g = Integer.parseInt(tokens[idx++]);
                int b = Integer.parseInt(tokens[idx++]);
                img.setPixel(x, y, r, g, b);
            }
        }
        return img;

    }

    public void save_bin(String filename) throws IOException {
        FileOutputStream writer = new FileOutputStream(filename);
        String header = "P6\n" + width + " " + height + "\n255\n";
        writer.write(header.getBytes(StandardCharsets.US_ASCII));

        for (int[][] y : pixels) {
            for (int[] x : y) {
                writer.write((byte)x[0]);
                writer.write((byte)x[1]);
                writer.write((byte)x[2]);
            }
        }

        writer.close();

    }

}