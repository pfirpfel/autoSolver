/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package g2048;

import java.util.HashSet;

/**
 *
 * @author Elias
 */
public abstract class AbstractGameSubject implements  GameSubject {
    private final HashSet<GameObserver> observers = new HashSet();

    @Override
    public void addGameObserver(GameObserver o) {
        observers.add(o);
    }

    @Override
    public void removeGameObserver(GameObserver o) {
        observers.remove(o);
    }
    
    protected void notifyObservers(int[][] state){
        HashSet<GameObserver> copy;
        synchronized(observers){
            copy = new HashSet(observers);
        }
        for(GameObserver o : copy){
            o.onStateChange(state);
        }
    }
}
