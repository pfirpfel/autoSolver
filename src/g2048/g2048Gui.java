/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2048;

import autosolver.AutoSolver;
import autosolver.GameRules;
import g2048.g2048.Direction;
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
    private final StandardFenster fenster;
    private final JPanel hintergrund;
    private final JMenuBar menu = new JMenuBar();
    private final JMenuItem tipp, neu, auto, nextStep;
    private final JButton[][] grid;
    private final JLabel punkte = new JLabel("score: 0");
    private final ImageIcon[] tiles = {
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
    private final int powersOfTwo[];
    private final g2048 game;
    private final GameRules nextGameStep;
    private AutoSolver autosolver;
    private boolean firstStart;

    public g2048Gui() {
        this.powersOfTwo = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
        this.hintergrund = new JPanel(new GridLayout(4, 4));
        this.grid = new JButton[4][4];
        this.firstStart = true;
        g2048 mainGame = new g2048();
        nextGameStep = new GameRules();
        restartThread();
        this.game = mainGame;
        this.game.addGameObserver(this);
        fenster = new StandardFenster(500, 500, "2048");
        fenster.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                drückPfeiltasten(evt.getKeyCode());
            }
        });
        hintergrund.setBackground(new Color(187, 173, 160));
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (JButton[] row : grid) {
            for (int j = 0; j < grid.length; j++) {
                row[j] = new JButton();
                row[j].setEnabled(false);
                hintergrund.add(row[j]);
            }
        }
        reset();
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

    private void restartThread() {
        autosolver = new AutoSolver(this, nextGameStep, game);
    }

    public void updateBoard(int[][] state) {
        int[][] b = state;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int icon = 0;
                if (b[i][j] > 0) {
                    icon = (int) (Math.log(b[i][j]) / Math.log(2));
                }
                grid[i][j].setIcon(tiles[icon]);
                grid[i][j].setDisabledIcon(tiles[icon]);
            }
        }
        this.punkte.setText("Punkte: " + game.getScore());
    }

    private void drückPfeiltasten(int e) {
        Direction direction = null;
        switch (e) {
            case 37:
                direction = Direction.LEFT;
                break;
            case 38:
                direction = Direction.UP;
                break;
            case 39:
                direction = Direction.RIGHT;
                break;
            case 40:
                direction = Direction.DOWN;
                break;
        }
        if (direction != null) {
            autosolver.pleaseStop();
            game.move(direction);
        }
    }

    private void getHinweis() {
        autosolver.pleaseStop();
        String message = "";
        switch (nextGameStep.simulate(game.getState())) {
            case LEFT:
                message = "Der nächste Schritt ist nach links";
                break;
            case RIGHT:
                message = "Der nächste Schritt ist nach rechts";
                break;
            case UP:
                message = "Der nächste Schritt ist nach oben";
                break;
            case DOWN:
                message = "Der nächste Schritt ist nach unten";
                break;
        }
        JOptionPane.showMessageDialog(null, message, "2048", JOptionPane.INFORMATION_MESSAGE);
    }

    private void reset() {
        autosolver.pleaseStop();
        game.reset();
    }

    private void auto() {
        if (autosolver.isCalculating()) {
            autosolver.pleaseStop();
        } else {
            restartThread();
            autosolver.start();
        }
    }

    private void nextStep() {
        autosolver.pleaseStop();
        Direction suggestedStep = nextGameStep.simulate(game.getState());
        game.move(suggestedStep);
    }

    public void nextMove(Direction a) {
        game.move(a);
    }

    @Override
    public void onStateChange(int[][] state) {
        this.updateBoard(state);
    }
    public static void main(String[] args) {
        g2048Gui hallo = new g2048Gui();
    }
}
