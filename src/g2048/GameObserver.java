package g2048;

/**
 *
 * @author Elias
 */
public interface GameObserver {

    /**
     *
     * @param state
     */
    public void onStateChange(int[][] state);
}
