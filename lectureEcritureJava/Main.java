import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) {
        String enTete = "P3\n100 100\n255\n";

        try {
            RandomAccessFile raf = new RandomAccessFile("firstPPM_withRAF.ppm", "rw");
            raf.writeBytes(enTete.getBytes()); // écrit le texte en octets
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}