/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elias
 */
public class AutoSolverTest {

    //KeyAction k1 = new KeyAction();
    GameState s1 = new GameState();
    GameRules r1 = new GameRules();

    @Test
    public void testUp() throws Exception {
        int grid[][] = {{0, 0, 0, 0},
        {0, 0, 0, 0},
        {16, 8, 4, 2},
        {2, 4, 8, 16}};
        r1.simulate(grid);
        System.out.println("expect up/38");
        System.out.println("resultat " + r1.simulate(grid));
        assertEquals(r1.simulate(grid), 38);
    }

    @Test
    public void testRight() throws Exception {
        int grid[][] = {{2, 16, 0, 0},
        {4, 8, 0, 0},
        {8, 4, 0, 0},
        {16, 2, 0, 0}};
        r1.simulate(grid);
        System.out.println("expect right/39");
        System.out.println("resultat " + r1.simulate(grid));
        assertEquals(r1.simulate(grid), 39);
    }

    @Test
    public void testDown() throws Exception {
        int grid[][] = {{16, 8, 4, 2},
        {2, 4, 8, 16},
        {0, 0, 0, 0},
        {0, 0, 0, 0}};
        r1.simulate(grid);
        System.out.println("expect down/40");
        System.out.println("resultat " + r1.simulate(grid));
        assertEquals(r1.simulate(grid), 40);
    }

    @Test
    public void testLeft() throws Exception {
        int grid[][] = {{0, 0, 16, 2},
        {0, 0, 8, 4},
        {0, 0, 4, 8},
        {0, 0, 2, 16}};
        r1.simulate(grid);
        System.out.println("expect left/37");
        System.out.println("resultat " + r1.simulate(grid));
        assertEquals(r1.simulate(grid), 37);
    }
}
