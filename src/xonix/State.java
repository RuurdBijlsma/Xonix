package xonix;

/**
 * Contains state of the game
 */
class State {
    private static State instance = null;

    public static State getInstance() {
        if (instance == null) {
            instance = new State();
        }
        return instance;
    }

    private int level;
    private float clock;
    private int lives;
    private int currentScore;
    private int requiredScore;
    private boolean gameOver;

    private State() {
        this.reset();
    }

    /**
     * Resets state
     */
    void reset() {
        setLevel(1);
    }

    int getLevel() {
        return level;
    }

    /**
     * Set the level, a higher leven means higher required score and less given time
     * @param level level that has been reached
     */
    private void setLevel(int level) {
        this.level = level;
        this.clock = (20 - level) * 2;
        this.lives = 3;
        this.currentScore = 0;
        this.requiredScore = (40 + level * 10) * 100;
        this.gameOver = false;
    }

    float getClock() {
        return clock;
    }

    void setClock(float clock) {
        this.clock = clock;
        if ((int) clock == 0) {
            decreaseLives();
            this.clock = (6 - level) * 2;
        }
    }

    void addClock(float clock) {
        setClock(this.clock + clock);
    }

    int getLives() {
        return lives;
    }

    private void setLives(int lives) {
        if (lives <= 0)
            setGameOver(true);
        this.lives = lives;
    }

    void decreaseLives() {
        setLives(getLives() - 1);
    }

    int getCurrentScore() {
        return currentScore;
    }

    private void setCurrentScore(int cscore) {
        this.currentScore = cscore;
        if (cscore > requiredScore)
            setLevel(level + 1);
    }

    void addCurrentScore(int currentScore) {
        setCurrentScore(this.currentScore + currentScore);
    }

    int getRequiredScore() {
        return requiredScore;
    }

    public void setRequiredScore(int requiredScore) {
        this.requiredScore = requiredScore;
    }

    boolean isGameOver() {
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
