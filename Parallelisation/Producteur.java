import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;



class Producteur implements Runnable {

    
    private static final int TAILLE_BUFFER = 5;
    private final Integer[] buffer = new Integer[TAILLE_BUFFER];

    // TODO: Déclarer 3 sémaphores avec les bonnes valeurs initiales
    private final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
    private final Semaphore elementsDisponibles = new Semaphore(0);  
    private final Semaphore mutexBuffer = new Semaphore(1); // quel erreur ici ? vous pouvez le résoudre plus tard -- c'est un mutex classique pas besoin d'un semaphore

    @override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int produit = i;

            try{
                
                //Attendre une place libre
                placesLibres.acquire();
                //Acquérir l'accès exclusif au buffer 
                mutexBuffer.acquire();
                //Ajouter l'élément au buffer
                produit = buffer[i];
                //Libérer l'accès au buffer
                mutexBuffer.release();
                //Signaler qu'un élément est disponible
                elementsDisponibles.release();
            
            } catch (InterruptedException e){
                System.out.println("erreur");
            }
        }
    }
}