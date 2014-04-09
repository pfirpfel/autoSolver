package g2048;

import javax.swing.JOptionPane;

/**
 * this class contains the main game of 2048 with all logical functions
 * excluding Gui
 *
 * @author Elias
 */
public class g2048 extends AbstractGameSubject {

    private int grid[][], score;//speichern des Spielzustandes
    private final int freeGrids[][];//speichern der Postitionen der freien Grids
    private int zaehlerFreeGrids;//speichert die Anzahl der freien Grids
    private boolean ende; //speichert ob das Spiel zu Ende ist

    /**
     * creates a new game and sets the variables to default. Creates two random
     * numbers in the Grid
     */
    public g2048() {
        this.ende = false;
        this.zaehlerFreeGrids = 0;
        this.freeGrids = new int[16][2];
        reset();
    }

    /**
     * resets the game and sets the variables to default. Creates two random
     * numbers in the Grid
     */
    public void reset() {
        score = 0;
        ende = false;
        grid = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int index = 0; index < 4; index++) {
                grid[index][i] = 0;
            }
        }
        getFreeGrids(grid);
        grid = setzteZufall(grid);
        getFreeGrids(grid);
        grid = setzteZufall(grid);
        this.notifyObservers(clone2DArray(grid));
    }

    /**
     * @return returns a copy of the current State of the Game
     */
    public int[][] getState() {
        //liefert eine Kopie des aktuellen Status
        int copy[][] = clone2DArray(grid);
        return copy;
    }

    /**
     * @return returns the current score of the Game
     */
    public int getScore() {
        return score;
    }

    /**
     * @return retruns if the Game is over or not -true/false
     */
    public boolean getEnde() {
        return ende;
    }

    private void gewonnenVerloren() {//calculates if the game is over
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] >= 2048) {//if one of the numbers reached 2048, you won
                    ende = true;
                    JOptionPane.showMessageDialog(null, "Bravo sie haben gewonnen", "2048", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        int scoreBefore = score;//if you can't make any more moves and you haven't yet reached 2048, you lost
        if (!gridChanged(grid, up()) && !gridChanged(grid, down()) && !gridChanged(grid, right()) && !gridChanged(grid, left())) {
            ende = true;
            score = scoreBefore;
            JOptionPane.showMessageDialog(null, "Sie haben verloren", "2048", JOptionPane.INFORMATION_MESSAGE);
        }
        score = scoreBefore;
    }

    /**
     * contains all permitted moves/directions
     */
    public enum Direction {

        UP, DOWN, LEFT, RIGHT
    }

    /**
     * moves the grid according to the given direction and claculates if the
     * game is over
     *
     * @param direction
     */
    public void move(Direction direction) {
        int resGrid[][] = new int[4][4];
        switch (direction) {
            case UP:
                resGrid = up();
                break;
            case DOWN:
                resGrid = down();
                break;
            case LEFT:
                resGrid = left();
                break;
            case RIGHT:
                resGrid = right();
                break;
        }
        if (gridChanged(grid, resGrid)) {
            getFreeGrids(resGrid);
            grid = setzteZufall(resGrid);
            this.notifyObservers(clone2DArray(grid));

        }
        gewonnenVerloren();
    }

    private int[][] up() {//all numbers will go up and combine when possible
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
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == egrid[yIndex][xIndex]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex + 1][xIndex] = 0;
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

    private int[][] right() {//all numbers will right and combine when possible
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
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex] == egrid[yIndex][xIndex - 1]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex - 1] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] = 0;
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

    private int[][] down() {//all numbers will down and combine when possible
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
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == egrid[yIndex][xIndex]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex - 1][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] = 0;
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

    private int[][] left() {//all numbers will go left and combine when possible
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
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex] == egrid[yIndex][xIndex + 1]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex + 1] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] = 0;
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

    /**
     *
     * @param array
     * @return returns a copy of the Array.
     */
    public static int[][] clone2DArray(int[][] array) {
        int[][] clone = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, clone[i], 0, array[i].length);
        }
        return clone;
    }

    private void getFreeGrids(int grid[][]) {//calculates all free Grids
        for (int i = 0; i < 16; i++) {
            freeGrids[i][0] = 0;
            freeGrids[i][1] = 0;
        }
        zaehlerFreeGrids = 0;      //number of grids with 0
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids[zaehlerFreeGrids][0] = index;
                    freeGrids[zaehlerFreeGrids][1] = i;
                    zaehlerFreeGrids++;
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
        }//gives back if the Grid has changed or not
        return false;
    }

    private int[][] setzteZufall(int[][] resGrid) {//sets an random number at one of the free grids
        int a = (int) (Math.random() * zaehlerFreeGrids);
        int b = (int) (Math.random() * 10);
        if (b == 5) {
            resGrid[freeGrids[a][0]][freeGrids[a][1]] = 4;
        } else {
            resGrid[freeGrids[a][0]][freeGrids[a][1]] = 2;
        }
        return resGrid;
    }
}
