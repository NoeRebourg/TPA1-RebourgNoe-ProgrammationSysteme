public class CalculParallele {
    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;
    private static Calcule[] threads = new Calcule[DONNEES.length];

    public static void main(String[] args) {
        System.out.println("=== Calcul Parallele ===");

        long debut = System.nanoTime();
        long somme = 0;
        

        // TODO: Ce code sera remplacé par une version parallèle
        for (int i = 0; i < DONNEES.length; i++) {
            threads[i] = new Calcule(DONNEES[i], MULTIPLICATEUR, i);
            threads[i].start();
        }

        for (int i = 0; i < DONNEES.length; i++){
            try{
                threads[i].join();
                somme += threads[i].getResultat();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
            
        

        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + somme);
        System.out.println("Durée : " + duree + " ms");
    }
}