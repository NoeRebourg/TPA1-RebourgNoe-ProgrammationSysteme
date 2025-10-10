public class Calcule extends Thread {
    //Déclarer les attributs nécessaires
    private int valeur;
    private int multiplicateur;
    private int index;

    public Calcule(int valeur, int multiplicateur, int index){
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
    }

    @Override
    public void run() {
        // TODO: Implémenter le calcul intensif
        long resultat = 0;
        for (int j = 0; j < MULTIPLICATEUR; j++) {
            resultat += valeur * valeur + valeur;
        }
        // Même logique que dans la boucle interne de CalculSequentiel
    }

}

