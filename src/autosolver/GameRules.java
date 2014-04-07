/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 *
 * @author Elias
 */
public class GameRules {

    double freeGridStart, freeGridUp, freeGridRight, freeGridDown, freeGridLeft;
//    boolean moveableUp,moveableRight,moveableDown,moveableLeft;
    //the game starts with two random numbers at random positions
    //can only go up/down/right/left wiith the arrows
    //it create with an probability of 90% a 2 or 10% a 4 in one of the emty places
    //two numbers of the same value can be merged togehter for an higher number

    //simulates the move and gives back the new number of free Grids
    public int simulate(int grid[][]) {
        freeGridStart = getAnzahlFreeGrids(grid);
        simulateStep1(grid);
        //grösste zahl = beste methode
        //überprüft welches die grösste zahl ist und liefert den key dazu;
        double best = freeGridUp;
        int key = KeyEvent.VK_UP;
        if (best < freeGridDown) {
            best = freeGridDown;
            key = KeyEvent.VK_DOWN;
        }
        if (best < freeGridRight) {
            best = freeGridRight;
            key = KeyEvent.VK_RIGHT;
        }
        if (best < freeGridLeft) {
            key = KeyEvent.VK_LEFT;
        }
        return key;
    }

    private void simulateStep1(int grid[][]) {

        int gridUp[][] = this.up(grid);
        Arrays.copyOf(grid, grid.length);
        freeGridUp = getAnzahlFreeGrids(gridUp);
        if (gridChanged(gridUp, grid)) {
            freeGridUp+=simulateNextMove(gridUp);
        } else {
            freeGridUp -= 100;
        }
        int gridDown[][] = this.down(Arrays.copyOf(grid, grid.length));
        freeGridDown = getAnzahlFreeGrids(gridDown);
        if (gridChanged(gridDown, grid)) {
            freeGridDown+=simulateNextMove(gridDown);
        } else {
            freeGridDown -= 100;
        }
        int gridRight[][] = this.right(Arrays.copyOf(grid, grid.length));
        freeGridRight = getAnzahlFreeGrids(gridRight);

        if (gridChanged(gridRight, grid)) {
            freeGridRight+=simulateNextMove(gridRight);
        } else {
            freeGridRight -= 100;
        }
        int gridLeft[][] = this.left(Arrays.copyOf(grid, grid.length));
        freeGridLeft = getAnzahlFreeGrids(gridLeft);
        if (gridChanged(gridLeft, grid)) {
            freeGridLeft+=simulateNextMove(gridLeft);
        } else {
            freeGridLeft -= 100;
        }
    }
//simulates the move and gives back the Grids

    private double simulateNextMove(int egrid[][]) {
        double bestResultat = -100;
        int anzahlFreieFelder = getAnzahlFreeGrids(egrid);
        int freieFelder[][] = getFreeGrids(egrid, anzahlFreieFelder);
       // int[][] resGrid = clone2DArray(egrid);

        for (int i = 0; i < anzahlFreieFelder; i++) {
            int[][] testGrid = clone2DArray(egrid);
            double Resultat;
            testGrid[freieFelder[i][0]][freieFelder[i][1]] = 2;
            int[][] resGrid = right(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = left(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = up(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = down(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            // gleich 4j
            testGrid[freieFelder[i][0]][freieFelder[i][1]] = 4;
            resGrid = right(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = left(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = up(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = down(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
                Resultat+=simulateFutureMove(resGrid);
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
        }
        return bestResultat;
    }
    private double simulateFutureMove(int egrid[][]) {
        double bestResultat = -100;
        int anzahlFreieFelder = getAnzahlFreeGrids(egrid);
        int freieFelder[][] = getFreeGrids(egrid, anzahlFreieFelder);
       // int[][] resGrid = clone2DArray(egrid);

        for (int i = 0; i < anzahlFreieFelder; i++) {
            int[][] testGrid = clone2DArray(egrid);
            double Resultat;
            testGrid[freieFelder[i][0]][freieFelder[i][1]] = 2;
            int[][] resGrid = right(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = left(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = up(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = down(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.9;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            // gleich 4j
            testGrid[freieFelder[i][0]][freieFelder[i][1]] = 4;
            resGrid = right(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = left(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = up(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
            resGrid = down(clone2DArray(testGrid));
            if (gridChanged(resGrid, testGrid)) {
                Resultat = getAnzahlFreeGrids(resGrid) * 1.1;
            } else {
                Resultat = -100;
            }
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
            }
        }
        return bestResultat;
    }
    public int[][] up(int egrid[][]) {
        //all numbers will go up and combine when possible
        int[][] grid = clone2DArray(egrid);
        for (int yIndex = 3; yIndex > 0; yIndex--) {
            for (int xIndex = 0; xIndex < 4; xIndex++) {
                if (grid[yIndex - 1][xIndex] == grid[yIndex][xIndex]) {
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex - 1][xIndex] = 0;
                }
                if (grid[yIndex - 1][xIndex] == 0) {
                    for (int a = yIndex - 1; a < 3; a++) {
                        grid[a][xIndex] = grid[a + 1][xIndex];
                    }
                    grid[3][xIndex] = 0;
                }
            }
        }
        return grid;
    }

    //simulates the move and gives back the Grids
    public int[][] right(int egrid[][]) {
        //all numbers will right up and combine when possible
        int[][] grid = clone2DArray(egrid);
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (grid[yIndex][xIndex] == grid[yIndex][xIndex + 1]) {
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex + 1] = 0;
                }
                if (grid[yIndex][xIndex + 1] == 0) {
                    for (int a = xIndex + 1; a > 0; a--) {
                        grid[yIndex][a] = grid[yIndex][a - 1];
                    }
                    grid[yIndex][0] = 0;
                }
            }
        }
        return grid;
    }
//simulates the move and gives back the Grids

    public int[][] down(int egrid[][]) {
        //all numbers will down up and combine when possible
        int[][] grid = clone2DArray(egrid);
        for (int yIndex = 0; yIndex < 3; yIndex++) {
            for (int xIndex = 0; xIndex < 4; xIndex++) {
                if (grid[yIndex + 1][xIndex] == grid[yIndex][xIndex]) {
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex + 1][xIndex] = 0;
                }
                if (grid[yIndex + 1][xIndex] == 0) {
                    for (int a = yIndex + 1; a > 0; a--) {
                        grid[a][xIndex] = grid[a - 1][xIndex];
                    }
                    grid[0][xIndex] = 0;
                }
            }
        }
        return grid;
    }

    //simulates the move and gives back the Grids
    public int[][] left(int egrid[][]) {
        //all numbers will go left and combine when possible
        int[][] grid = clone2DArray(egrid);
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (grid[yIndex][xIndex] == grid[yIndex][xIndex - 1]) {
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex - 1] = 0;
                }
                if (grid[yIndex][xIndex - 1] == 0) {
                    for (int a = xIndex - 1; a < 3; a++) {
                        grid[yIndex][a] = grid[yIndex][a + 1];
                    }
                    grid[yIndex][3] = 0;
                }
            }
        }
        return grid;
    }

    private void zeicheResultat(int grid[][]) {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                System.out.print(grid[index][i] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static int[][] clone2DArray(int[][] array) {
        int rows = array.length;
    //int rowIs=array[0].length ;

        //clone the 'shallow' structure of array
        int[][] newArray = (int[][]) array.clone();
        //clone the 'deep' structure of array
        for (int row = 0; row < rows; row++) {
            newArray[row] = (int[]) array[row].clone();
        }

        return newArray;
    }

    public int getAnzahlFreeGrids(int grid[][]) {
        int freeGrids = 0;//number of grids with 0
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids++;
                }
            }
        }
        return freeGrids;
    }

    private int[][] getFreeGrids(int grid[][], int zaehler) {
        int freeGrids[][] = new int[zaehler][2];
        zaehler=0;
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids[zaehler][0] = index;
                    freeGrids[zaehler][1] = i;
                    zaehler++;
                }
            }
        }
        return freeGrids;
    }

    public boolean gridChanged(int gridNeu[][], int gritAlt[][]) {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (gridNeu[index][i] != gritAlt[index][i]) {
                    return true;
                }
            }
        }
        return false;
    }
}
