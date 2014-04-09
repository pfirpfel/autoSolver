package g2048;

/**
 *
 * @author Elias
 */
public interface GameSubject {

    /**
     *
     * @param o
     */
    public void addGameObserver(GameObserver o);

    /**
     *
     * @param o
     */
    public void removeGameObserver(GameObserver o);
}
