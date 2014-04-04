/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2048;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
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
        //ICon
        Image icon = new ImageIcon("battlefield.png").getImage();
        setIconImage(icon);
        //not
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}
