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

    public int testMethod(int grid[][]) throws AWTException {
        GameRules r1 = new GameRules();
        return (r1.simulate(grid));
    }

    public static void main(String[] args) throws AWTException {
        KeyAction k1 = new KeyAction();
        GameState s1 = new GameState();
        GameRules r1 = new GameRules();
        //Aktion
        //int grid[][]=s1.getState();
        int grid[][] = {{0, 2, 4, 8},
        {4, 2, 0, 2},
        {16, 32, 2, 8},
        {16, 4, 4, 32}};
        k1.keyPress(r1.simulate(grid));
        int grid2[][] = {{0, 2, 4, 8},
        {0, 2, 0, 0},
        {16, 32, 2, 8},
        {16, 4, 4, 0}};
        k1.keyPress(r1.simulate(grid2));
        int grid3[][] = {{16, 2, 0, 2},
        {0, 2, 4, 2},
        {2, 2, 2, 2},
        {16, 4, 4, 0}};
        k1.keyPress(r1.simulate(grid2));
        int grid4[][] = {{0, 2, 4, 8},
        {0, 2, 0, 16},
        {16, 32, 2, 8},
        {0, 2, 4, 2}};
        k1.keyPress(r1.simulate(grid2));
    }
}
