/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autosolver;

import java.awt.event.KeyEvent;

/**
 *
 * @author Elias
 */
public class GameRules {
    int freeGridStart,freeGridUp,freeGridRight,freeGridDown,freeGridLeft;
//    boolean moveableUp,moveableRight,moveableDown,moveableLeft;
    //the game starts with two random numbers at random positions
    //can only go up/down/right/left wiith the arrows
    //it create with an probability of 90% a 2 or 10% a 4 in one of the emty places
    //two numbers of the same value can be merged togehter for an higher number
    GameState g1 = new GameState();

    //simulates the move and gives back the new number of free Grids
    public int simulate(int grid[][]) {
        freeGridStart=g1.gerFreeGrids(grid);
        simulateStep1(grid);
        //grösste zahl = beste methode
        if(freeGridUp>=freeGridDown&&freeGridUp>=freeGridRight&&freeGridUp>=freeGridLeft){
            return KeyEvent.VK_UP;
        }else if(freeGridRight>=freeGridLeft&&freeGridRight>=freeGridDown){
            return KeyEvent.VK_RIGHT;
        }else if(freeGridLeft>=freeGridDown){
            return KeyEvent.VK_LEFT;
        }else{
        return KeyEvent.VK_DOWN;
        }
    }
    private void simulateStep1(int grid[][]){
        int gridUp[][] = this.up(grid);
        if(g1.gridChanged(gridUp)==true){
            freeGridUp = g1.gerFreeGrids(gridUp);
            simulateUp(gridUp);
        }else{
            freeGridUp=-100;
        }
        int gridDown[][] = this.down(grid);
        if(g1.gridChanged(gridDown)==true){
            freeGridDown = g1.gerFreeGrids(gridDown);
            simulateDown(gridDown);
        }else{
            freeGridDown=-100;
        }  
        int gridRight[][] = this.up(grid);
        if(g1.gridChanged(gridRight)==true){
            freeGridRight = g1.gerFreeGrids(gridRight);
            simulateRight(gridRight);
        }else{
            freeGridRight=-100;
        }
        int gridLeft[][] = this.down(grid);
        if(g1.gridChanged(gridLeft)==true){
            freeGridLeft = g1.gerFreeGrids(gridLeft);
            simulateLeft(gridLeft);
        }else{
            freeGridLeft=-100;
        } 
    }
//simulates the move and gives back the Grids
    private void simulateRight(int grid[][]){
        
    }
    private void simulateDown(int grid[][]){
        
    }
    private void simulateLeft(int grid[][]){
        
    }
    private void simulateUp(int grid[][]){
        
    }
    private int[][] up(int grid[][]) {
        //all numbers will go up and combine when possible
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 3; i++) {
                if (grid[index][i] == 0) {
                    for (int a = i; a < 3; a++) {
                        grid[index][i] = grid[index][i + 1];
                    }
                } else if (grid[index][i] == grid[index][i + 1]) {
                    grid[index][i] += grid[index][i];
                    for (int a = i + 1; a < 4; a++) {
                        grid[index][i] = grid[index][i + 1];
                    }
                }
            }
        }
        return grid;
    }

    //simulates the move and gives back the Grids
    private int[][] right(int grid[][]) {
        //all numbers will right up and combine when possible
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    for (int a = index; a < 4; a++) {
                        grid[index][i] = grid[index + 1][i];
                    }
                } else if (grid[index][i] == grid[index + 1][i]) {
                    grid[index][i] += grid[index][i];
                    for (int a = index + 1; a < 4; a++) {
                        grid[index][i] = grid[index + 1][i];
                    }
                }
            }
        }
        return grid;
    }
//simulates the move and gives back the Grids

    private int[][] down(int grid[][]) {
        //all numbers will down up and combine when possible
        for (int index = 3; index >= 0; index--) {
            for (int i = 3; i > 0; i--) {
                if (grid[index][i] == 0) {
                    for (int a = i; a > 0; a--) {
                        grid[index][i] = grid[index][i - 1];
                    }
                } else if (grid[index][i] == grid[index][i - 1]) {
                    grid[index][i] += grid[index][i];
                    for (int a = i - 1; a > 0; a--) {
                        grid[index][i] = grid[index][i - 1];
                    }
                }
            }
        }
        return grid;
    }

    //simulates the move and gives back the Grids
    private int[][] left(int grid[][]) {
        //all numbers will go left and combine when possible
        for (int index = 3; index >= 0; index--) {
            for (int i = 3; i >= 0; i--) {
                if (grid[index][i] == 0) {
                    for (int a = index; a > 0; a--) {
                        grid[index][i] = grid[index - 1][i];
                    }
                } else if (grid[index][i] == grid[index - 1][i]) {
                    grid[index][i] += grid[index][i];
                    for (int a = index - 1; a > 0; a--) {
                        grid[index][i] = grid[index - 1][i];
                    }
                }
            }
        }
        return grid;
    }
}
