/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import g2048.g2048;
import g2048.g2048.Direction;
import g2048.g2048Gui;

/**
 *
 * @author Elias
 */
public class AutoSolver extends Thread {

    private final GameRules nextStep;
    private final g2048Gui gameGui;
    private final g2048 game;
    private boolean ende;
    private boolean calculating;
    volatile boolean keepRunning;

    public AutoSolver(g2048Gui hallo, GameRules g1, g2048 gam) {
        this.calculating = false;
        this.keepRunning = true;
        this.ende = false;
        nextStep = g1;
        gameGui = hallo;
        game = gam;
        setDaemon(true);
    }

    public void pleaseStop() {
        keepRunning = false;
        calculating=false;
    }
    public boolean isCalculating(){
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
