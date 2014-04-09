package g2048;

/**
 * This class contains the main method to start the game
 *
 * @author Elias
 */
public class Starter {

    /**
     * this ist the main methode that is called to start the game
     *
     * @param args
     */
    public static void main(String[] args) {
        StandardFenster fenster = new StandardFenster(500, 500, "2048");
        g2048Gui hallo = new g2048Gui(fenster);
        
    }
}
