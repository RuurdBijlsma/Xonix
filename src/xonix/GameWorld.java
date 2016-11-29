package xonix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class containing the world, including timetickets, car and monsterballs
 */
class GameWorld {

    static final int SQUARE_LENGTH = 102;
    static final int SQUARE_UNITS = 5;
    static final int GAME_TICK_DELAY = 40;
    //    static final Color NO_COLOR = Color.white;
    static final Color CAR_COLOR = Color.red;
    static final Color SQUARE_COLOR = Color.black;
    static final Color LINE_COLOR = Color.red.darker().darker();
    static final Color PLAYER_COLOR = Color.cyan;
    private final Color MONSTER_COLOR = Color.orange;
    private final Color TICKET_COLOR = Color.green;
    private final int LEVEL_START = 1;
    //    static final int CLOCK_START = (6 - LEVEL_START) * 2;
//    static final int LIVES_START = 3;
//    static final int CSCORE_START = 0;
//    static final int RSCORE_START = (40 + LEVEL_START * 10) * 100;
    private final int TIME_START = 55 - LEVEL_START;

    private final GameView gameView;
    final FieldSquares fieldSquares;
    final Car car;
    private final Random random;
    ArrayList<MonsterBall> monsterBalls;
    ArrayList<TimeTicket> timeTickets;
    final State state;

    GameWorld() {
        random = new Random();
        gameView = new GameView();
        gameView.setWorld(this);
        fieldSquares = new FieldSquares();
        createMonsterballs();
        createTimeTickets();
        car = new Car(new Point2D.Float(SQUARE_LENGTH / 2 * SQUARE_UNITS, (SQUARE_LENGTH - 1) * SQUARE_UNITS), CAR_COLOR, 270, 50, SQUARE_UNITS, SQUARE_UNITS);
        state = new State();
        gameView.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                execute(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.play();
    }

    /**
     * @return Size of the gameworld in pixels
     */
    public int getSize() {
        float squareSize = fieldSquares.elementAt(0, 0).getSize();
        int numSquares = SQUARE_LENGTH;
        return (int) (numSquares * squareSize);
    }

    /**
     * @param fieldSquares The object which contains all field squares
     * @param position     The position from which you want the FieldSquare object
     * @return A field square object at the given position
     */
    static FieldSquare getSquareAtPosition(FieldSquares fieldSquares, Point2D.Float position) {
        return fieldSquares.elementAt((int) (position.x / GameWorld.SQUARE_UNITS + 0.5), (int) (position.y / GameWorld.SQUARE_UNITS + 0.5));
    }

    /**
     * @param fieldSquares The object which contains all field squares
     * @param x            x value of the position from which you want the FieldSquare object
     * @param y            y value of the position from which you want the FieldSquare object
     * @return A field square object at the given position
     */
    static FieldSquare getSquareAtPosition(FieldSquares fieldSquares, float x, float y) {
        return getSquareAtPosition(fieldSquares, new Point2D.Float(x, y));
    }

    /**
     * Creates monsterballs and adds them to the monsterBalls array
     */
    private void createMonsterballs() {
        monsterBalls = new ArrayList<>();
        int number = random.nextInt(10) + 1;
        for (int i = 0; i < number; i++) {
            Point2D.Float location = new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15);
            MonsterBall ball = new MonsterBall(location, MONSTER_COLOR, random.nextInt(360), random.nextFloat() * 100 + 10, 6);
            monsterBalls.add(ball);
        }
    }

    /**
     * Creates timertickets and adds them to the timeTickets array
     */
    private void createTimeTickets() {
        timeTickets = new ArrayList<>();
        int number = random.nextInt(SQUARE_UNITS) + 1;
        for (int i = 0; i < number; i++) {
            Point2D.Float location = new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15);
            int ticketSize = 7;
            TimeTicket ticket = new TimeTicket(location, TICKET_COLOR, TIME_START, ticketSize, ticketSize);
            timeTickets.add(ticket);
        }
    }

    /**
     * Starts gameloop, calling update every tick
     */
    private void play() {
        gameView.score.update();
        new Timer(GAME_TICK_DELAY, evt -> update((float) (GAME_TICK_DELAY / 1000.0))).start();
    }

    /**
     * Updates view and state with proper information
     * @param delta Delta time since previous frame
     */
    private void update(float delta) {
        if (!state.isGameOver()) {
            state.addClock(-delta);
            for (MonsterBall monsterBall : monsterBalls)
                if (monsterBall.changeLocation(fieldSquares, delta, null)) {//if monsterball collides with player line
                    state.decreaseLives();
                    monsterBalls.remove(monsterBall);
                    break;
                }
            car.changeLocation(fieldSquares, delta, state);
            for (TimeTicket timeTicket : timeTickets)
                if (timeTicket.contains(car.getLocation())) {
                    state.setClock(state.getClock() + timeTicket.getSeconds());
                    timeTickets.remove(timeTicket);
                    gameView.score.update();
                    break;
                }
        }
        gameView.update();
    }

    /**
     * Resets fieldSquares, monsterBalls, timeTickets, car and state
     */
    private void reset() {
        this.fieldSquares.reset();
        createMonsterballs();
        createTimeTickets();
        this.car.reset();
        this.state.reset();
    }

    /**
     * Handles key events
     * @param keycode code of pressed key
     */
    private void execute(int keycode) {
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                car.setHeading(180);
                break;
            case KeyEvent.VK_UP:
                car.setHeading(90);
                break;
            case KeyEvent.VK_RIGHT:
                car.setHeading(0);
                break;
            case KeyEvent.VK_DOWN:
                car.setHeading(270);
                break;
            case KeyEvent.VK_SPACE:
                if (state.isGameOver())
                    reset();
                break;
            case KeyEvent.VK_I:
                car.setSpeed(car.getSpeed() + 5);
                break;
            case KeyEvent.VK_K:
                timeTickets.add(new TimeTicket(new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), TICKET_COLOR, TIME_START, 7, 7));
                break;
            case KeyEvent.VK_L:
                car.setSpeed(car.getSpeed() - 5);
                break;
            case KeyEvent.VK_M:
                monsterBalls.add(new MonsterBall(new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), MONSTER_COLOR, random.nextInt(360), random.nextFloat() * 100 + 10, 6));
                break;
        }
    }
}
