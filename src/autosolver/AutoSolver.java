/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import g2048.g2048;
import g2048.g2048Gui;

/**
 *
 * @author Elias
 */
public class AutoSolver extends Thread {

    GameRules nextStep;
    g2048Gui gameGui;
    g2048 game;

    public AutoSolver(g2048Gui hallo, GameRules g1, g2048 gam) {
        nextStep = g1;
        gameGui = hallo;
        game = gam;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            int grid[][] = game.getState();
            int direction = nextStep.simulate(grid);
            gameGui.nextMove(direction);
            try {
                //Thread.sleep(100);
            } catch (Exception ex) {
                interrupt();
                break;
            }
        }
    }
}
