package autosolver;

import g2048.g2048;
import g2048.g2048.Direction;
import g2048.g2048Gui;

/**
 * Thread der in einer Endlosschleife das Spiel weiterberechnet bis er
 * angehalten wird
 *
 * @author Elias
 */
public class AutoSolver extends Thread {

    //Variablen
    private final GameRules nextStep;
    private final g2048Gui gameGui;
    private final g2048 game;
    private boolean ende;
    private boolean calculating;
    volatile boolean keepRunning;

    /**
     * Konstruktor der alle mitgegebenen und Start-Variablen setzt.
     *
     * @param hallo
     * @param g1
     * @param gam
     */
    public AutoSolver(g2048Gui hallo, GameRules g1, g2048 gam) {
        this.calculating = false;
        this.keepRunning = true;
        this.ende = false;
        nextStep = g1;
        gameGui = hallo;
        game = gam;
        setDaemon(true);
    }

    /**
     * Funktion zum anhalten des Threads
     */
    public void pleaseStop() {
        keepRunning = false;
        calculating = false;
    }

    /**
     * Funktion gibt zurück ob der Thread läuft/lebt oder angehalten wurde.
     *
     * @return
     */
    public boolean isCalculating() {
        return calculating;
    }

    @Override
    public void run() {
        calculating = true;
        this.keepRunning = true;
        this.ende = false;
        while (keepRunning & !ende) {
            int grid[][] = game.getState();
            Direction direction = nextStep.simulate(grid);
            gameGui.nextMove(direction);
            ende = game.getEnde();
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                interrupt();
                break;
            }
        }
    }
}
