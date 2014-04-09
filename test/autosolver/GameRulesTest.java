/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import static g2048.g2048.Direction.*;
import static g2048.g2048.clone2DArray;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elias
 */
public class GameRulesTest {

    GameRules r1 = new GameRules();

    /**
     * testing of the simulate-method with the expected outcome to be UP
     *
     * @throws Exception
     */
    @Test
    public void testResultatUp() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 2}, {16, 8, 4, 2}, {2, 4, 8, 16}};
        System.out.println("expect up/38");
        assertEquals(UP, r1.simulate(grid));
    }

    /**
     * testing of the simulate-method with the expected outcome to be Right
     *
     * @throws Exception
     */
    @Test
    public void testResultatRight() throws Exception {
        int grid[][] = {{2, 16, 0, 0}, {4, 8, 0, 0}, {8, 4, 0, 0}, {16, 2, 0, 0}};
        System.out.println("expect right/39");
        assertEquals(RIGHT, r1.simulate(grid));
    }

    /**
     * testing of the simulate-method with the expected outcome to be down
     *
     * @throws Exception
     */
    @Test
    public void testResultatDown() throws Exception {
        int grid[][] = {{16, 8, 4, 2}, {2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        System.out.println("expect down/40");
        assertEquals(DOWN, r1.simulate(grid));
    }

    /**
     * testing of the simulate-method with the expected outcome to be left
     *
     * @throws Exception
     */
    @Test
    public void testResultatLeft() throws Exception {
        int grid[][] = {{0, 0, 16, 2}, {0, 0, 8, 4}, {0, 0, 4, 8}, {0, 0, 2, 16}};
        System.out.println("expect left/37");
        assertEquals(LEFT, r1.simulate(grid));
    }

    /**
     * testing of the FreeGrids-method with the expected outcome to be 0
     *
     * @throws Exception
     */
    @Test
    public void testFreeGrids0() throws Exception {
        int grid[][] = {{16, 4, 16, 2}, {2, 8, 8, 4}, {2, 2, 4, 8}, {2, 2, 2, 16}};
        assertEquals(0, r1.getAnzahlFreeGrids(grid));
    }

    /**
     * testing of the FreeGrids-method with the expected outcome to be 16
     *
     * @throws Exception
     */
    @Test
    public void testFreeGrids16() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertEquals(16, r1.getAnzahlFreeGrids(grid));
    }

    /**
     * testing of the GridChanged-method with the expected outcome to be true
     *
     * @throws Exception
     */
    @Test
    public void testGridChangedTrue() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{16, 4, 16, 2}, {2, 8, 8, 4}, {2, 2, 4, 8}, {2, 2, 2, 16}};
        assertEquals(true, r1.gridChanged(grid, grid2));
    }

    /**
     * testing of the GridChanged-method with the expected outcome to be false
     *
     * @throws Exception
     */
    @Test
    public void testGridChangedFalse() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertEquals(false, r1.gridChanged(grid, grid2));
    }

    /**
     * testing of the up-method
     *
     * @throws Exception
     */
    @Test
    public void testUp() throws Exception {
        int grid[][] = {{2, 8, 2, 0}, {2, 8, 0, 0}, {2, 16, 2, 0}, {4, 32, 4, 0}};
        int grid2[][] = {{4, 16, 4, 0}, {2, 16, 4, 0}, {4, 32, 0, 0}, {0, 0, 0, 0}};
        System.out.println("Up:");
        assertArrayEquals(grid2, r1.up(grid));
    }

    /**
     * testing of the right-method
     *
     * @throws Exception
     */
    @Test
    public void testRight() throws Exception {
        int grid[][] = {{2, 2, 2, 0}, {4, 4, 4, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{0, 0, 2, 4}, {0, 0, 4, 8}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertArrayEquals(grid2, r1.right(grid));
    }

    /**
     * testing of the down-method
     *
     * @throws Exception
     */
    @Test
    public void testDown() throws Exception {
        int grid[][] = {{2, 8, 2, 0}, {2, 8, 0, 0}, {2, 16, 2, 0}, {4, 32, 4, 0}};
        int grid2[][] = {{0, 0, 0, 0}, {2, 16, 0, 0}, {4, 16, 4, 0}, {4, 32, 4, 0}};
        assertArrayEquals(grid2, r1.down(grid));

    }

    /**
     * testing of the left-method
     *
     * @throws Exception
     */
    @Test
    public void testLeft() throws Exception {
        int grid[][] = {{2, 2, 2, 0}, {4, 4, 4, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{4, 2, 0, 0}, {8, 4, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertArrayEquals(grid2, r1.left(grid));
    }

    /**
     * testing of the simulate-method with an comlex grid with the expected
     * outcome to be UP
     *
     * @throws Exception
     */
    @Test
    public void testComplex1() throws Exception {
        int grid[][] = {{4, 2, 4, 2}, {16, 32, 256, 2}, {1024, 64, 16, 128}, {2, 4, 16, 4}};
        int grid2[][] = {{32, 2, 0, 0}, {4, 8, 4, 0}, {4, 8, 0, 0}, {4, 16, 0, 0}};
        assertEquals(UP, r1.simulate(clone2DArray(grid)));
    }
}
