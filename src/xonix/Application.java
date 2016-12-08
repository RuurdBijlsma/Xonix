package xonix;

/**
 * Main class
 */
public class Application {
    /**
     * Initializes gameworld
     */
    public static GameController controller;

    public static void main(String[] args) {
        controller = GameController.getInstance();
        controller.initialize();
    }
}
