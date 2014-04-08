/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import g2048.g2048.Direction;
import java.awt.event.KeyEvent;

/**
 *
 * @author Elias
 */
public class GameRules {

    //Varaiblendefinition
    double freeGridStart, freeGridUp, freeGridRight, freeGridDown, freeGridLeft;
    int zaehler, score;

    //gives back the best possible move
    public Direction simulate(int grid[][]) {
        freeGridStart = getAnzahlFreeGrids(grid);
        switch(simulateStep1(grid)){
            case 0:
               return Direction.RIGHT;
           case 1:
               return Direction.LEFT;
           case 2:
               return Direction.UP;
           default:
               return Direction.DOWN;
        }
    }

    //simulates the first Step
    private int simulateStep1(int grid[][]) {
        //Variablendefiniton
        double bestResultat = -100;
        int bestDirection = 0;
        int[][] resGrid;
        //für alle freien Felder
        //werden für alle möglichen Richtungen die Ergebnisse simuliert
        for (int richtung = 0; richtung < 4; richtung++) {
            if (richtung == 0) {
                resGrid = right(clone2DArray(grid));
            } else if (richtung == 1) {
                resGrid = left(clone2DArray(grid));
            } else if (richtung == 2) {
                resGrid = up(clone2DArray(grid));
            } else {
                resGrid = down(clone2DArray(grid));
            }
            double Resultat;
            //wenn etwas passiert ist
            if (gridChanged(resGrid, grid)) {//soll er auszählen was es bringt
                Resultat = getAnzahlFreeGrids(resGrid);
                Resultat += score;
                zaehler = 0;
                Resultat += simulateFutureMove(resGrid);
            } else {//wenn nichts passiert ist gib einen negativen Wert zurück
                Resultat = -100;
            }//wenn das aktuelle Resultat besser ist als die vorherigen speichere das aktuelle ab mit richtung
//                System.out.println(richtung+" : "+Resultat);
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
                bestDirection = richtung;
            }
        }//gib sie beste Richtung zurück
        return bestDirection;
    }

    public int[][] up(int grid[][]) {
        //all numbers will go up and combine when possible
        score = 0;
        for (int xIndex = 0; xIndex < 4; xIndex++) {
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (grid[yIndex - 1][xIndex] == 0) {
                    for (int a = yIndex - 1; a < 3; a++) {
                        grid[a][xIndex] = grid[a + 1][xIndex];
                    }
                    grid[3][xIndex] = 0;
                }
            }
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (grid[yIndex - 1][xIndex] == grid[yIndex][xIndex]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex - 1][xIndex] = 0;
                }
            }
            for (int yIndex = 3; yIndex > 0; yIndex--) {
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

    public int[][] right(int grid[][]) {
        //all numbers will right up and combine when possible
        score = 0;
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (grid[yIndex][xIndex + 1] == 0) {
                    for (int a = xIndex + 1; a > 0; a--) {
                        grid[yIndex][a] = grid[yIndex][a - 1];
                    }
                    grid[yIndex][0] = 0;
                }
            }
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (grid[yIndex][xIndex] == grid[yIndex][xIndex + 1]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex + 1] = 0;
                }
            }
            for (int xIndex = 0; xIndex < 3; xIndex++) {
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

    public int[][] down(int grid[][]) {
        //all numbers will down up and combine when possible
        score = 0;
        for (int xIndex = 0; xIndex < 4; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (grid[yIndex + 1][xIndex] == 0) {
                    for (int a = yIndex + 1; a > 0; a--) {
                        grid[a][xIndex] = grid[a - 1][xIndex];
                    }
                    grid[0][xIndex] = 0;
                }
            }
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (grid[yIndex + 1][xIndex] == grid[yIndex][xIndex]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex + 1][xIndex] = 0;
                }

            }
            for (int yIndex = 0; yIndex < 3; yIndex++) {
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

    public int[][] left(int grid[][]) {
        //all numbers will go left and combine when possible
        score = 0;
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (grid[yIndex][xIndex - 1] == 0) {
                    for (int a = xIndex - 1; a < 3; a++) {
                        grid[yIndex][a] = grid[yIndex][a + 1];
                    }
                    grid[yIndex][3] = 0;
                }
            }
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (grid[yIndex][xIndex] == grid[yIndex][xIndex - 1]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex - 1] = 0;
                }
            }
            for (int xIndex = 3; xIndex > 0; xIndex--) {
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

    public int getAnzahlFreeGrids(int grid[][]) {
        int freeGrids = 0;//number of grids with 0
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids++;
                }
            }
        }//liefert die Anzahl aktuell freier Grids zurück
        return freeGrids;
    }

    private int[][] getFreeGrids(int grid[][], int zaehler) {
        int freeGrids[][] = new int[zaehler][2];
        zaehler = 0;
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids[zaehler][0] = index;
                    freeGrids[zaehler][1] = i;
                    zaehler++;
                }
            }
        }//lierfert die Postiionen aller freien Grids zurück
        return freeGrids;
    }

    public boolean gridChanged(int gridNeu[][], int gritAlt[][]) {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (gridNeu[index][i] != gritAlt[index][i]) {
                    return true;
                }
            }
        }//lierfert zurück ob sich das Grid geändert hat oder nicht
        return false;
    }

    private double simulateFutureMove(int egrid[][]) {
        zaehler++;//Zähler wird bei jedem Aufruf erhöht
        int anzahlFreieFelder = getAnzahlFreeGrids(egrid);
        int maxzaehler = 2;
        if (anzahlFreieFelder < 12) {
            maxzaehler = 2;
            if (anzahlFreieFelder < 6) {
                maxzaehler = 3;
             if (anzahlFreieFelder<3){
                 maxzaehler=4;
             }
            }
        }
        if (zaehler > maxzaehler) {//wenn er grösser ist als die maximal zulässige Zahl soll abgebrochen werden und 0 zurück gegeben werden.
            score = 0;
            return 0;
        }
        double bestResultat = -100;
        int freieFelder[][] = getFreeGrids(egrid, anzahlFreieFelder);
        int[][] resGrid;
        for (int bin = 0; bin < 2; bin++) {
            //es werden alle fällle für 2 und 4 simuliert
            int binero = 0;
            double multi = 0;
            if (bin == 0) {
                binero = 2;
                multi = 1.9;
            } else {
                binero = 4;
                multi = 1.1;
            }//es werden für alle freien Felder simmuliert
            for (int i = 0; i < anzahlFreieFelder; i++) {
                int[][] testGrid = clone2DArray(egrid);
                testGrid[freieFelder[i][0]][freieFelder[i][1]] = binero;
                //es werden für alle Richtungen simuliert
                for (int richtung = 0; richtung < 4; richtung++) {
                    if (richtung == 0) {
                        resGrid = right(clone2DArray(testGrid));
                    } else if (richtung == 1) {
                        resGrid = left(clone2DArray(testGrid));
                    } else if (richtung == 2) {
                        resGrid = up(clone2DArray(testGrid));
                    } else {
                        resGrid = down(clone2DArray(testGrid));
                    }
                    double Resultat = 0;
                    if (gridChanged(resGrid, testGrid)) {
                        Resultat += score;
                        Resultat += getAnzahlFreeGrids(resGrid) * (multi);
                        Resultat += simulateFutureMove(resGrid);
                        zaehler--;
                    } else {
                        Resultat = (-100) / Math.pow(2, zaehler);
                    }
//                    Resultat=Resultat/Math.pow(2, zaehler);
                    if (bestResultat < Resultat) {
                        bestResultat = Resultat;
                    }
                }
            }
        }
        return bestResultat;
    }

    public void zeicheResultat(int grid[][]) {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                System.out.print(grid[index][i] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
