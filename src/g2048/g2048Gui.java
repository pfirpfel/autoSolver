/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2048;

import autosolver.AutoSolver;
import autosolver.GameRules;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Elias
 */
public class g2048Gui implements GameObserver {

    //Varaiblendefinition
    StandardFenster fenster;
    JPanel hintergrund = new JPanel(new GridLayout(4, 4));
    JMenuBar menu = new JMenuBar();
    JMenuItem tipp, neu, auto, nextStep;
    JButton[][] grid = new JButton[4][4];
    JLabel punkte = new JLabel("score: 0");
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
    int bineros[][] = {{0, 1}, {1, 2}, {2, 4}, {3, 8}, {4, 16}, {5, 32}, {6, 64}, {7, 128}, {8, 256}, {9, 512}, {10, 1024}, {11, 2048}};
    //gjh
    g2048 game;
    GameRules nextGameStep;
    Thread autosolver;
    boolean firstStart = true;

    public g2048Gui() {
        g2048 mainGame = new g2048();
        GameRules nextGameStep = new GameRules();
        Thread autosolver = new Thread(new AutoSolver(this, nextGameStep, mainGame));
//gj
        this.game = mainGame;
        this.nextGameStep = nextGameStep;
        this.autosolver = autosolver;
        //fh
        fenster = new StandardFenster(500, 500, "2048");
        fenster.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                drückPfeiltasten(evt.getKeyCode());
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
        reset();
        updateBoard();
        fenster.add(hintergrund);

        fenster.setLocationRelativeTo(null);
        fenster.setVisible(true);
        tipp = new JMenuItem("tipp");
        tipp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                getHinweis();
            }
        });
        menu.add(tipp);
        neu = new JMenuItem("neu");
        neu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                reset();
            }
        });
        menu.add(neu);
        auto = new JMenuItem("Auto");
        auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                auto();
            }
        });
        menu.add(auto);
        nextStep = new JMenuItem("next Step");
        nextStep.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                nextStep();
            }
        });
        menu.add(nextStep);
        fenster.setJMenuBar(menu);
        menu.setVisible(true);
        fenster.pack();
        menu.add(punkte);
    }

    public void updateBoard(int[][] state) {
        int[][] b = state;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
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
        this.punkte.setText("Punkte: " + game.getScore());
    }

    private void drückPfeiltasten(int e) {
        if (e >= 37 && e <= 40) {
            if (!autosolver.isInterrupted()) {
                autosolver.interrupt();
            }
            game.move(e);
            updateBoard();
        }
    }

    private void getHinweis() {
        autosolver.interrupt();
        int[][] b = game.getState();
        int a = nextGameStep.simulate(b);
        if (a == 37) {
            JOptionPane.showMessageDialog(null, "Der nächste Schritt ist nach links", "2048", JOptionPane.PLAIN_MESSAGE);
        } else if (a == 39) {
            JOptionPane.showMessageDialog(null, "Der nächste Schritt ist nach rechts", "2048", JOptionPane.PLAIN_MESSAGE);
        } else if (a == 38) {
            JOptionPane.showMessageDialog(null, "Der nächste Schritt ist nach oben", "2048", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Der nächste Schritt ist nach unten", "2048", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void reset() {
        //autosolver.interrupt();
        game.reset();
        int[][] b = game.getState();
        updateBoard();
    }

    private void auto() {
        if (autosolver.isAlive()) {
            autosolver.interrupt();

        } else {
            if (firstStart) {
                autosolver.start();
                firstStart = false;
            } else {
                autosolver.interrupt();//Fehler
            }
        }
    }

    private void nextStep() {
        autosolver.interrupt();
        int[][] b = game.getState();
        int a = nextGameStep.simulate(b);
        game.move(a);
        updateBoard();
    }

    public void nextMove(int a) {
        game.move(a);
        updateBoard();
    }

    @Override
    public void onStateChange(int[][] state) {
        this.updateBoard(state);
    }
}
