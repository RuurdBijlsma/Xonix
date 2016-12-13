package xonix;

import xonix.Controller.GameController;

/**
 * Main class
 */
public class Application {
    /**
     * Initializes gameworld
     */
    public static GameController controller;

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "True");
        controller = GameController.getInstance();
        controller.initialize();
    }
}
