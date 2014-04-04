/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2048;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Elias
 */
public class g2048Gui {

    //Varaiblendefinition
    StandardFenster fenster;
    JPanel hintergrund = new JPanel(new GridLayout(4, 4));
    JMenuBar menu = new JMenuBar();
    JMenuItem tipp, neu, auto;
    JButton[][] grid = new JButton[4][4];
    ImageIcon[] tiles = {
        new ImageIcon(this.getClass().getResource("./images/1.png")),
        new ImageIcon(this.getClass().getResource("./images/2.png")),
        new ImageIcon(this.getClass().getResource("./images/4.png")),
        new ImageIcon(this.getClass().getResource("./images/8.png")),
        new ImageIcon(this.getClass().getResource("./images/16.png")),
        new ImageIcon(this.getClass().getResource("./images/32.png")),
        new ImageIcon(this.getClass().getResource("./images/64.png")),
        new ImageIcon(this.getClass().getResource("./images/128.png")),
        new ImageIcon(this.getClass().getResource("./images/256.png")),
        new ImageIcon(this.getClass().getResource("./images/512.png")),
        new ImageIcon(this.getClass().getResource("./images/1024.png")),
        new ImageIcon(this.getClass().getResource("./images/2048.png"))
    };
    int bineros[][] = {{0, 0}, {1, 2}, {2, 4}, {3, 8}, {4, 16}, {5, 32}, {6, 64}, {7, 128}, {8, 256}, {9, 512}, {10, 1024}, {11, 2048}};
//	Main control

    public g2048Gui() {
//		m = control;
        fenster = new StandardFenster(500, 500, "2048");
        fenster.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                drückPfeiltasten(evt);
            }
        });
        hintergrund.setBackground(new Color(187, 173, 160));
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setEnabled(false);
                hintergrund.add(grid[i][j]);
            }
        }

        updateBoard();
        //fenster.add(hintergrund);
        fenster.pack();
        fenster.setLocationRelativeTo(null);
        fenster.setVisible(true);
        tipp = new JMenuItem("tipp");
        tipp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                //Aktion beim drücken
            }
        });
        menu.add(tipp);
        neu = new JMenu("neu");
        neu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                //Aktion beim drücken
            }
        });
        menu.add(neu);
        auto = new JMenuItem("Auto");
        auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                //Aktion beim drücken
            }
        });
        menu.add(auto);
        fenster.setJMenuBar(menu);
        menu.setVisible(true);
    }

    public void updateBoard() {
//        int[][] b = m.cst.board;
        int b[][] = new int[4][4];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                b[i][j] = 1024;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                // hardcoded because i'm dumb
                int k = 0;
                for (int bin = 0; bin < 12; bin++) {
                    if (b[i][j] == bineros[bin][1]) {
                        k = bineros[bin][0];
                        break;
                    }
                }
                grid[i][j].setIcon(tiles[k]);
                grid[i][j].setDisabledIcon(tiles[k]);
            }
        }
    }

    private void drückPfeiltasten(KeyEvent e) {
//		ArrayList<State> s = U.generateSuccessors(m.cst);
//		switch(e.getKeyCode()) {
//		case KeyEvent.VK_UP: 	m.cst = s.get(0); break;
//		case KeyEvent.VK_DOWN:  m.cst = s.get(1); break;
//		case KeyEvent.VK_LEFT:  m.cst = s.get(2); break;
//		case KeyEvent.VK_RIGHT: m.cst = s.get(3); break;
//		}
//		m.cst.board = U.addRandomTile(m.cst.board);
//		System.out.println("score: "+U.getScore(m.cst));
//		updateBoard();
    }

    public static void main(String[] args) {
        g2048Gui hallo = new g2048Gui();
    }
}
