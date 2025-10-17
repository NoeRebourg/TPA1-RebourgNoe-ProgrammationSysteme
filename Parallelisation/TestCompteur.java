public class TestCompteur{

    public static void main(String args[]){
        System.out.println("Test de CompteurDangereux");
        Thread[] threads = new Thread[50];
        for(int i = 0; i < 50; i++){
            CompteurDangereux.resetCompteur();
            threads[i] 
        }
        for(int i = 0; i < 50; i++){
            threads[i].join();
        }
    }
}