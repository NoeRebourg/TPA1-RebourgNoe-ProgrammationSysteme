public class Calcule extends Thread { //ou implements Rumable
    //Déclarer les attributs nécessaires
    private int valeur;
    private int multiplicateur;
    private int index;
    private long resultat;

    public Calcule(int valeur, int multiplicateur, int index){
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
    }

    @Override
    public void run() {
        // TODO: Implémenter le calcul intensif
        long temp = 0;
        for (int j = 0; j < multiplicateur; j++) {
            temp += valeur * valeur + valeur;
        }
        // Même logique que dans la boucle interne de CalculSequentiel
    }

    public long getResultat(){
        return resultat;
    }



}

