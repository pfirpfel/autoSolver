package g2048;

import java.util.HashSet;

/**
 *
 * @author Elias
 */
public abstract class AbstractGameSubject implements GameSubject {

    private final HashSet<GameObserver> observers = new HashSet();

    /**
     * adds the GameObserver o
     *
     * @param o
     */
    @Override
    public void addGameObserver(GameObserver o) {
        observers.add(o);
    }

    /**
     * removes the GameObserver o
     *
     * @param o
     */
    @Override
    public void removeGameObserver(GameObserver o) {
        observers.remove(o);
    }

    /**
     * notifys the Observers
     *
     * @param state
     */
    protected void notifyObservers(int[][] state) {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (GameObserver o : copy) {
            o.onStateChange(state);
        }
    }
}
