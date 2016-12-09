package xonix.Model;

import xonix.Commands.NextLevel;

import java.awt.event.ActionEvent;

/**
 * Contains state of the game
 */
public class RealState implements IState {
    private static RealState instance = null;
    private int level;
    private float clock;
    private int lives;
    private int currentScore;
    private int requiredScore;
    private boolean gameOver;
    private ProxyState proxy;

    private RealState() {
        reset();
        proxy = new ProxyState(this);
    }

    public static RealState getInstance() {
        if (instance == null) {
            instance = new RealState();
        }
        return instance;
    }

    public ProxyState getProxy() {
        return proxy;
    }

    /**
     * Resets state
     */
    void reset() {
        setLevel(1);
    }

    public int getLevel() {
        return level;
    }

    /**
     * Set the level, a higher leven means higher required score and less given time
     *
     * @param level level that has been reached
     */
    public void setLevel(int level) {
        this.level = level;
        this.clock = (20 - level) * 2;
        this.lives = 3;
        this.currentScore = 0;
        this.requiredScore = (40 + level * 10) * 100;
        this.gameOver = false;
    }

    public float getClock() {
        return clock;
    }

    public void setClock(float clock) {
        this.clock = clock;
        if ((int) clock == 0) {
            decreaseLives();
            this.clock = (6 - level) * 2;
        }
    }

    void addClock(float clock) {
        setClock(this.clock + clock);
    }

    public int getLives() {
        return lives;
    }

    private void setLives(int lives) {
        if (lives <= 0)
            setGameOver(true);
        this.lives = lives;
    }

    public void decreaseLives() {
        setLives(getLives() - 1);
    }

    public int getCurrentScore() {
        return currentScore;
    }

    private void setCurrentScore(int cscore) {
        this.currentScore = cscore;
        if (cscore > requiredScore) {
            NextLevel nextLevel = new NextLevel();
            nextLevel.actionPerformed(new ActionEvent(this, 0, "NextLevel"));
        }
    }

    void addCurrentScore(int currentScore) {
        setCurrentScore(this.currentScore + currentScore);
    }

    public int getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    /**
     * @return String containing state information
     */
    @Override
    public String toString() {
        return "Current level=" + this.getLevel()
                + " Remaining lives=" + this.getLives()
                + " Remaining time=" + this.getClock()
                + " Current score=" + this.getCurrentScore()
                + " Required score=" + this.getRequiredScore();
    }
}
