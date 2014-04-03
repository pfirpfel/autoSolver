/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

/**
 *
 * @author Elias
 */
public class GameState {

    private int grid[][] = new int[4][4];//to save the State of the Game
    private int score;//to save the score of the game
    GameState child;

    private void GameState() {
        //when the class is loaded the actuell State should be read out of the JavaScript
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                grid[index][i] = 0;
            }
        }
    }

    private void getScore() {

    }

    public int[][] getState() {
        return grid;
    }

    public int gerFreeGrids(int grid[][]) {
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

    public boolean gridChanged(int grid[][]) {

        return true;
    }
}
