/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

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
    public void testResultatUp() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 2}, {16, 8, 4, 2}, {2, 4, 8, 16}};
        System.out.println("expect up/38");
        assertEquals(38, r1.simulate(grid));
    }

    @Test
    public void testResultatRight() throws Exception {
        int grid[][] = {{2, 16, 0, 0}, {4, 8, 0, 0}, {8, 4, 0, 0}, {16, 2, 0, 0}};
        System.out.println("expect right/39");
        assertEquals(39, r1.simulate(grid));
    }

    @Test
    public void testResultatDown() throws Exception {
        int grid[][] = {{16, 8, 4, 2}, {2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        System.out.println("expect down/40");
        assertEquals(40, r1.simulate(grid));
    }

    @Test
    public void testResultatLeft() throws Exception {
        int grid[][] = {{0, 0, 16, 2}, {0, 0, 8, 4}, {0, 0, 4, 8}, {0, 0, 2, 16}};
        System.out.println("expect left/37");
        assertEquals(37, r1.simulate(grid));
    }

    @Test
    public void testFreeGrids0() throws Exception {
        int grid[][] = {{16, 4, 16, 2}, {2, 8, 8, 4}, {2, 2, 4, 8}, {2, 2, 2, 16}};
        assertEquals(0, s1.gerFreeGrids(grid));
    }

    @Test
    public void testFreeGrids16() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertEquals(16, s1.gerFreeGrids(grid));
    }

    @Test
    public void testGridChangedTrue() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{16, 4, 16, 2}, {2, 8, 8, 4}, {2, 2, 4, 8}, {2, 2, 2, 16}};
        assertEquals(true, s1.gridChanged(grid, grid2));
    }

    @Test
    public void testGridChangedFalse() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertEquals(false, s1.gridChanged(grid, grid2));
    }

    @Test
    public void testUp() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {16, 0, 0, 2}, {16, 8, 4, 2}, {2, 4, 8, 16}};
        int grid2[][] = {{32, 8, 4, 4}, {2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        assertArrayEquals(grid2, r1.up(grid));
    }

    @Test
    public void testRight() throws Exception {
        int grid[][] = {{2, 16, 0, 16}, {4, 8, 8, 0}, {8, 4, 0, 0}, {16, 2, 0, 0}};
        int grid2[][] = {{0, 0, 2, 32}, {0, 0, 4, 16}, {0, 0, 8, 4}, {0, 0, 16, 2}};
        assertArrayEquals(grid2, r1.right(grid));
    }

    @Test
    public void testDown() throws Exception {
        int grid[][] = {{16, 8, 4, 16}, {2, 4, 8, 16}, {0, 4, 8, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {16, 8, 4, 0}, {2, 8, 16, 32}};
        assertArrayEquals(grid2, r1.down(grid));

    }

    @Test
    public void testLeft() throws Exception {
        int grid[][] = {{16, 0, 16, 2}, {0, 4, 8, 4}, {0, 0, 4, 8}, {0, 2, 2, 16}};
        int grid2[][] = {{32, 2, 0, 0}, {4, 8, 4, 0}, {4, 8, 0, 0}, {4, 16, 0, 0}};
        assertArrayEquals(grid2, r1.left(grid));
    }
}
