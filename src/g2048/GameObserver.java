/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2048;

/**
 *
 * @author Elias
 */
public interface GameObserver {
    public void onStateChange(int[][] state);
}
