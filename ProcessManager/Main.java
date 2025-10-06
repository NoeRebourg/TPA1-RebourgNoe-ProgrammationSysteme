/*
 *
*/
public class Main{

    public static void main(String[] args){

        ProcessController controller = new ProcessController();
        
        System.out.println("Etape 1");

        String[] args1 = {"Hello world"};
        try{
            controller.executeSimple("echo",args1);
        } catch(Exception e) {
            System.out.println("Erreur" + e.getMessage());
            e.printStackTrace();
        }

    
        System.out.println("Etape 2");

        String[] args2 = {"Hello world"};
        try{
            controller.executeWithRedirection("echo", new File("OutputFile.txt"), new File("ErrorFile.txt"), args2); //TODO régler l'erreur
        } catch(Exception e) {
            System.out.println("Erreur + e.getMessage()");
            e.printStackTrace();
        }
    }
}