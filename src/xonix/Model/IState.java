package xonix.Model;

/**
 * Created by Ruurd on 8-12-2016.
 */
public interface IState {
    int getLevel();

    float getClock();

    int getLives();

    int getCurrentScore();

    int getRequiredScore();

    boolean isGameOver();

    @Override
    String toString();
}
