public class TestMutex {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Test Compteur Securise ===");
        CompteurSecurise.resetCompteur();

        Thread[] threads = new Thread[50];

        for (int i = 0 ; i < 50; i++) {
            CompteurSecurise.Incrementeur incrementeur = new CompteurSecurise.Incrementeur("incrementeur-" + i, 100);
            threads[i] = new Thread(incrementeur);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Le compteur final est " + CompteurSecurise.getCompteur());

    }
}