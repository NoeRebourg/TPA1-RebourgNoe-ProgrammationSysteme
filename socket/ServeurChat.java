import java.net.*;
import java.io.*;
import java.util.*;

public class ServeurChat {
    // Liste thread-safe des flux de sortie
    private static final List<PrintWriter> clients = 
        Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Serveur Chat démarré");

        while (true) {
            Socket client = serverSocket.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            synchronized (clients) {
                clients.add(out);
            }
            System.out.println("Client connecté. Total : " + clients.size());
            new Thread(new ChatHandler(client, out)).start();
        }
    }

    static class ChatHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ChatHandler(Socket socket, PrintWriter out) {
            this.socket = socket;
            this.out = out;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                String ligne;
                while ((ligne = in.readLine()) != null) {
                    System.out.println("Message reçu : " + ligne);
                    broadcastExcept(ligne);  // Diffuse à tous sauf l'émetteur
                }
            } catch(IOException error){ 
            }
            finally {
                synchronized (clients) {
                    clients.remove(out);
                }
                try { socket.close(); } catch (IOException ignored) {}
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (PrintWriter writer : clients) {
                    writer.println(">>> " + message);
                }
            }   
        }

        private void broadcastExcept(String message) {
            synchronized (clients) {
                for (PrintWriter writer : clients) {
                    if (writer != out) {  // Exclut l'émetteur
                        writer.println(">>> " + message);
                    }
                }
            }
        }
    }
}