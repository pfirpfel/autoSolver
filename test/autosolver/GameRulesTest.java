/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import static g2048.g2048.Direction.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elias
 */
public class GameRulesTest {

    //KeyAction k1 = new KeyAction();
    GameRules r1 = new GameRules();

    @Test
    public void testResultatUp() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 2}, {16, 8, 4, 2}, {2, 4, 8, 16}};
        System.out.println("expect up/38");
        assertEquals(UP, r1.simulate(grid));
    }

    @Test
    public void testResultatRight() throws Exception {
        int grid[][] = {{2, 16, 0, 0}, {4, 8, 0, 0}, {8, 4, 0, 0}, {16, 2, 0, 0}};
        System.out.println("expect right/39");
        assertEquals(RIGHT, r1.simulate(grid));
    }

    @Test
    public void testResultatDown() throws Exception {
        int grid[][] = {{16, 8, 4, 2}, {2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        System.out.println("expect down/40");
        assertEquals(DOWN, r1.simulate(grid));
    }

    @Test
    public void testResultatLeft() throws Exception {
        int grid[][] = {{0, 0, 16, 2}, {0, 0, 8, 4}, {0, 0, 4, 8}, {0, 0, 2, 16}};
        System.out.println("expect left/37");
        assertEquals(LEFT, r1.simulate(grid));
    }

    @Test
    public void testFreeGrids0() throws Exception {
        int grid[][] = {{16, 4, 16, 2}, {2, 8, 8, 4}, {2, 2, 4, 8}, {2, 2, 2, 16}};
        assertEquals(0, r1.getAnzahlFreeGrids(grid));
    }

    @Test
    public void testFreeGrids16() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertEquals(16, r1.getAnzahlFreeGrids(grid));
    }

    @Test
    public void testGridChangedTrue() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{16, 4, 16, 2}, {2, 8, 8, 4}, {2, 2, 4, 8}, {2, 2, 2, 16}};
        assertEquals(true, r1.gridChanged(grid, grid2));
    }

    @Test
    public void testGridChangedFalse() throws Exception {
        int grid[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertEquals(false, r1.gridChanged(grid, grid2));
    }

    @Test
    public void testUp() throws Exception {
        int grid[][] = {{2, 8, 2, 0}, {2, 8, 0, 0}, {2, 16, 2, 0}, {4, 32, 4, 0}};
        int grid2[][] = {{4, 16, 4, 0}, {2, 16, 4, 0}, {4, 32, 0, 0}, {0, 0, 0, 0}};
        System.out.println("Up:");
        assertArrayEquals(grid2, r1.up(grid));
    }

    @Test
    public void testRight() throws Exception {
        int grid[][] = {{2, 2, 2, 0}, {4, 4, 4, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{0, 0, 2, 4}, {0, 0, 4, 8}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertArrayEquals(grid2, r1.right(grid));
    }

    @Test
    public void testDown() throws Exception {
        int grid[][] = {{2, 8, 2, 0}, {2, 8, 0, 0}, {2, 16, 2, 0}, {4, 32, 4, 0}};
        int grid2[][] = {{0, 0, 0, 0},{2, 16, 0, 0}, {4, 16, 4, 0}, {4, 32, 4, 0}};
        assertArrayEquals(grid2, r1.down(grid));

    }

    @Test
    public void testLeft() throws Exception {
        int grid[][] = {{2, 2, 2, 0}, {4, 4, 4, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int grid2[][] = {{4, 2, 0, 0}, {8, 4, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertArrayEquals(grid2, r1.left(grid));
    }

    @Test
    public void testComplex1() throws Exception {
        int grid[][] = {{4, 2, 4, 2}, {16, 32, 256, 2}, {1024, 64, 16, 128}, {2, 4, 16, 4}};
        int grid2[][] = {{32, 2, 0, 0}, {4, 8, 4, 0}, {4, 8, 0, 0}, {4, 16, 0, 0}};
        System.out.println("Up:");
        r1.zeicheResultat(r1.up(clone2DArray(grid)));
        System.out.println("Down:");
        r1.zeicheResultat(r1.down(clone2DArray(grid)));
        System.out.println("right:");
        r1.zeicheResultat(r1.right(clone2DArray(grid)));
        System.out.println("left:");
        r1.zeicheResultat(r1.left(clone2DArray(grid)));
        assertEquals(UP, r1.simulate(clone2DArray(grid)));
    }

    private static int[][] clone2DArray(int[][] array) {
        int rows = array.length;
        //clone the 'shallow' structure of array
        int[][] newArray = (int[][]) array.clone();
        //clone the 'deep' structure of array
        for (int row = 0; row < rows; row++) {
            newArray[row] = (int[]) array[row].clone();
        }
        return newArray;
    }
}
