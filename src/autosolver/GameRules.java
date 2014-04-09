package autosolver;

import g2048.g2048.Direction;
import static g2048.g2048.clone2DArray;

/**
 * Diese Klasse berechnet mit übergebenen Werten was der nächste logische
 * Speilzug ist und liefert diesen zurück
 *
 * @author Elias
 */
public class GameRules {

    //Varaiblendefinition
    int zaehler;//zählt die Tiefe der Vorausschaungs-berechnung
    int score; //zählt den Punktestand

    /**
     * berechnet mit den übergebenen Werten den nächsten Schritt.
     *
     * @param grid
     * @return the best possible move
     */
    public Direction simulate(int grid[][]) {
        switch (simulateStep1(grid)) {
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
        double bestResultat = -100; //speichert das best mögliche Resultat für diesen Schritt
        int bestDirection = 0; //speichert die Richtung, die das beste Resultat hat
        int[][] resGrid;//speichert die Resultate der Simulation
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
            if (bestResultat < Resultat) {
                bestResultat = Resultat;
                bestDirection = richtung;
            }
        }//gib die beste Richtung zurück
        return bestDirection;
    }

    /**
     * all numbers will go up and combine when possible. calculating the score
     * and Game State
     *
     * @param grid
     * @return GameState after this move
     */
    public int[][] up(int grid[][]) {
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
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (grid[yIndex + 1][xIndex] == grid[yIndex][xIndex]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex + 1][xIndex] = 0;
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

    /**
     * all numbers will go right and combine when possible. calculating the
     * score and Game State
     *
     * @param grid
     * @return GameState after this move
     */
    public int[][] right(int grid[][]) {
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
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (grid[yIndex][xIndex] == grid[yIndex][xIndex - 1]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex - 1] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] = 0;
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

    /**
     * all numbers will go down and combine when possible. calculating the score
     * and Game State
     *
     * @param grid
     * @return GameState after this move
     */
    public int[][] down(int grid[][]) {
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
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (grid[yIndex - 1][xIndex] == grid[yIndex][xIndex]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex - 1][xIndex] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] = 0;
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

    /**
     * all numbers will go left and combine when possible. calculating the score
     * and Game State
     *
     * @param grid
     * @return GameState after this move
     */
    public int[][] left(int grid[][]) {
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
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (grid[yIndex][xIndex] == grid[yIndex][xIndex + 1]) {
                    score += grid[yIndex][xIndex];
                    grid[yIndex][xIndex + 1] += grid[yIndex][xIndex];
                    grid[yIndex][xIndex] = 0;
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

    /**
     * @param grid
     * @return the number of Free Grids.
     */
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
        }//gibt eine Liste zurück mit den Positionen der freien Grids.
        return freeGrids;
    }

    /**
     *
     * @param gridNeu
     * @param gritAlt
     * @return returns if the Grid has changed or not -true/false
     */
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

    private double simulateFutureMove(int egrid[][]) {//simuliert zukünftige Schritte
        zaehler++;//Zähler wird bei jedem Aufruf erhöht
        int anzahlFreieFelder = getAnzahlFreeGrids(egrid);
        int maxzaehler = 2;//es wird geschaut wie viele Schritte in die Zukunft geschaut werden kann ohne die Rechenzeit zu überschreiten.
        if (anzahlFreieFelder < 12) {
            maxzaehler = 2;
            if (anzahlFreieFelder < 6) {
                maxzaehler = 3;
                if (anzahlFreieFelder < 3) {
                    maxzaehler = 4;
                }
            }
        }
        if (zaehler > maxzaehler) {//wenn er grösser ist als die maximal zulässige Zahl soll abgebrochen werden und 0 zurück gegeben werden.
            score = 0;
            return 0;
        }
        double bestResultat = -100;//speichert das beste Resulat
        int freieFelder[][] = getFreeGrids(egrid, anzahlFreieFelder);//Positionen der freine Grids
        int[][] resGrid; //Resultate Grid
        for (int bin = 0; bin < 2; bin++) {
            //es werden alle fällle für 2 und 4 simuliert
            int binero;//speichern der Zahl
            double multi; //speichern des Multiplikators mit dem das Resultat verechnet wird.
            if (bin == 0) {
                binero = 2;
                multi = 1.9;
            } else {
                binero = 4;
                multi = 1.1;
            }//es wird für alle möglichen freien Felder simmuliert
            for (int i = 0; i < anzahlFreieFelder; i++) {
                int[][] testGrid = clone2DArray(egrid);
                testGrid[freieFelder[i][0]][freieFelder[i][1]] = binero;
                //es wird für alle Richtungen simuliert
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
                    if (gridChanged(resGrid, testGrid)) {//wenn sich was geändert hat
                        Resultat += score;//simuliere weiter und speichere das Resultat
                        Resultat += getAnzahlFreeGrids(resGrid) * (multi);
                        Resultat += simulateFutureMove(resGrid);
                        zaehler--;
                    } else {//wenn nicht gib einen negativen Wert zurück der mit zunähmender Tiefe an Gewichtung verliert
                        Resultat = (-100) / Math.pow(2, zaehler);
                    }
                    if (bestResultat < Resultat) {//überprüfe welches das beste Resultat ist.
                        bestResultat = Resultat;
                    }
                }
            }
        }
        return bestResultat;//liefere das beste Resultat zurück
    }
}
