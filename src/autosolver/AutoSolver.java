/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import java.awt.AWTException;

/**
 *
 * @author Elias
 */
public class AutoSolver {

    public static void main(String[] args) throws AWTException {
        KeyAction k1= new KeyAction();
        GameState s1 = new GameState();
        GameRules r1 = new GameRules();
        int grid[][] = s1.getState();
        int keycode = r1.simulate(grid);
        k1.keyPress(keycode);
    }
}
