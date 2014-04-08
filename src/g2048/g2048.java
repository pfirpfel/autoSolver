/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2048;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Elias
 */
public class g2048 {

    private int grid[][], score;
    int freeGrids[][] = new int[16][2];
    int zaehler = 0;

    //erstellen eines neuen Spieles
    public g2048() {
        reset();
    }

    public void reset() {
        //resetet der Spielvariblen
        score = 0;
        grid = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int index = 0; index < 4; index++) {
                grid[index][i] = 0;
            }
        }
        grid = setzteZufall(grid);
        grid = setzteZufall(grid);
    }

    public int[][] getState() {
        //liefert eine Kopie des aktuellen Status
        int copy[][] = clone2DArray(grid);
        return copy;
    }

    public int getScore() {
        return score;
    }
    private void gewonnenVerloren(){
        for(int index=0;index<4;index++){
            for(int i=0;i<4;i++){
                if(grid[index][i]>=1024){
                    JOptionPane.showMessageDialog(null, "Bravo sie haben gewonnen", "2048", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        if(!gridChanged(grid, up())&&!gridChanged(grid, down())&&!gridChanged(grid, right())&&!gridChanged(grid, left())){
            JOptionPane.showMessageDialog(null, "Sie haben verloren", "2048", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int[][] move(int direction) {
        int resGrid[][] = new int[4][4];
        if (direction == KeyEvent.VK_UP) {
            resGrid = up();
        } else if (direction == KeyEvent.VK_DOWN) {
            resGrid = down();
        } else if (direction == KeyEvent.VK_RIGHT) {
            resGrid = right();
        } else if (direction == KeyEvent.VK_LEFT) {
            resGrid = left();
        }
        if (gridChanged(grid, resGrid)) {
            gerFreeGrids(resGrid);
            resGrid = setzteZufall(resGrid);
        }
        grid = clone2DArray(resGrid);
        gewonnenVerloren();
        return clone2DArray(resGrid);
    }

    private int[][] up() {
        //all numbers will go up and combine when possible
        int[][] egrid = clone2DArray(grid);
        for (int xIndex = 0; xIndex < 4; xIndex++) {
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == 0) {
                    for (int a = yIndex - 1; a < 3; a++) {
                        egrid[a][xIndex] = egrid[a + 1][xIndex];
                    }
                    egrid[3][xIndex] = 0;
                }
            }
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == egrid[yIndex][xIndex]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex - 1][xIndex] = 0;
                }
            }
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == 0) {
                    for (int a = yIndex - 1; a < 3; a++) {
                        egrid[a][xIndex] = egrid[a + 1][xIndex];
                    }
                    egrid[3][xIndex] = 0;
                }
            }
        }
        return egrid;
    }

    //simulates the move and gives back the Grids
    private int[][] right() {
        //all numbers will right up and combine when possible
        int[][] egrid = clone2DArray(grid);
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex + 1] == 0) {
                    for (int a = xIndex + 1; a > 0; a--) {
                        egrid[yIndex][a] = egrid[yIndex][a - 1];
                    }
                    egrid[yIndex][0] = 0;
                }
            }
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex] == egrid[yIndex][xIndex + 1]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex + 1] = 0;
                }
            }
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex + 1] == 0) {
                    for (int a = xIndex + 1; a > 0; a--) {
                        egrid[yIndex][a] = egrid[yIndex][a - 1];
                    }
                    egrid[yIndex][0] = 0;
                }
            }
        }
        return egrid;
    }
//simulates the move and gives back the Grids

    private int[][] down() {
        //all numbers will down up and combine when possible
        int[][] egrid = clone2DArray(grid);
        for (int xIndex = 0; xIndex < 4; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == 0) {
                    for (int a = yIndex + 1; a > 0; a--) {
                        egrid[a][xIndex] = egrid[a - 1][xIndex];
                    }
                    egrid[0][xIndex] = 0;
                }
            }
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == egrid[yIndex][xIndex]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex + 1][xIndex] = 0;
                }

            }
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == 0) {
                    for (int a = yIndex + 1; a > 0; a--) {
                        egrid[a][xIndex] = egrid[a - 1][xIndex];
                    }
                    egrid[0][xIndex] = 0;
                }
            }
        }
        return egrid;
    }

    //simulates the move and gives back the Grids
    private int[][] left() {
        //all numbers will go left and combine when possible
        int[][] egrid = clone2DArray(grid);
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex - 1] == 0) {
                    for (int a = xIndex - 1; a < 3; a++) {
                        egrid[yIndex][a] = egrid[yIndex][a + 1];
                    }
                    egrid[yIndex][3] = 0;
                }
            }
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex] == egrid[yIndex][xIndex - 1]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex - 1] = 0;
                }

            }
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex - 1] == 0) {
                    for (int a = xIndex - 1; a < 3; a++) {
                        egrid[yIndex][a] = egrid[yIndex][a + 1];
                    }
                    egrid[yIndex][3] = 0;
                }
            }
        }
        return egrid;
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

    private void gerFreeGrids(int grid[][]) {
        for (int i = 0; i < 16; i++) {
            freeGrids[i][0] = 0;
            freeGrids[i][1] = 0;
        }
        zaehler = 0;      //number of grids with 0
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids[zaehler][0] = index;
                    freeGrids[zaehler][1] = i;
                    zaehler++;
                }
            }
        }
    }

    private boolean gridChanged(int gridNeu[][], int gritAlt[][]) {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (gridNeu[index][i] != gritAlt[index][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[][] setzteZufall(int[][] resGrid) {
        int a = (int) (Math.random() * zaehler);
        int b = (int) (Math.random() * 10);
        if (b == 5) {
            resGrid[freeGrids[a][0]][freeGrids[a][1]] = 4;
        } else {
            resGrid[freeGrids[a][0]][freeGrids[a][1]] = 2;
        }
        return resGrid;
    }
}
