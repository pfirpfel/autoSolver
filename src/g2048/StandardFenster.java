package g2048;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *  erstellt ein Fendter mit den mitgegebenen massen und dem Titel.
 *  Standard mässig wird das Fenster; mit dem battlefield.png Icoon versehen. Die Grösse ist nicht veränderbar. es wird mit dem schliessenButton geschlossen
 *  diese Einstellungen können nachhergehend verändert werden.
 * @author Elias
 */
public class StandardFenster extends javax.swing.JFrame {

    /**
     * Erstellelt ein neues Fenster mit den angegebenen Daten
     *
     * @param breite
     * @param höhe
     * @param titel
     */
    public StandardFenster(int breite, int höhe, String titel) {
        setPreferredSize(new Dimension(breite, höhe));
        setSize(breite, höhe);
        setResizable(false);
        setTitle(titel);
        //Icon
        Image icon = new ImageIcon("images/battlefield.png").getImage();
        setIconImage(icon);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}
