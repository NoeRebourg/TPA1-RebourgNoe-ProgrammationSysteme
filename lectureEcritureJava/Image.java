import java.io.FileWriter;
import java.io.IOException;

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
    public Image(int width, int hauteur) {
        this.width = width;
        this.height = height;
        pixels = new int[hauteur][largeur][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
     */
    public void save_txt(String filename) throws IOException {

    }

    static public read_txt(String filename) throws IOException {
       byte[] data = fs.readAllBytes();
        String txt = new String(date, StandarCharset.UTF-8);
        int old = 0;
        int current = 0;
        String[] tokens = new [tokens];
        for(int i=0;i<tokens.length;i++){
            if(' '|'\n'){
                tokens[current] = txt.substring(old,i-1)
                old = i+1
                current = ++
            }
        }
        if(tokens[0] != 'P3'){
            throws new Exception("le token ne correspond pas");
            if(token.length <= 3){
                int largeur = Integer.parseInt(tokens[1]);
                int hauteur = Integer.parseInt(tokens[2]);
                int max = Integer.parseInt(tokens[3]);
            }
        }
    }
}