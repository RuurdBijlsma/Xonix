package xonix;

import xonix.Commands.MonsterBallCollision;
import xonix.Commands.TimeTicketCollision;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;


/**
 * Class containing the world, including timetickets, car and monsterballs
 */
public class GameWorld {
    private static GameWorld instance = null;

    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    public static final int SQUARE_LENGTH = 102;
    public static final int SQUARE_UNITS = 5;
    public static final int GAME_TICK_DELAY = 40;
    //    static final Color NO_COLOR = Color.white;
    static final Color CAR_COLOR = Color.red;
    public static final Color SQUARE_COLOR = Color.black;
    public static final Color LINE_COLOR = Color.red.darker().darker();
    public static final Color PLAYER_COLOR = Color.cyan;
    public static final Color MONSTER_COLOR = Color.orange;
    public static final Color TICKET_COLOR = Color.green;
    private static final int LEVEL_START = 1;
    public static final int TIME_START = 55 - LEVEL_START;

    public final FieldSquares fieldSquares;
    public final Car car;
    private final Random random;
    public ArrayList<MonsterBall> monsterBalls;
    public ArrayList<TimeTicket> timeTickets;
    public final State state;

    private GameWorld() {
        random = new Random();
        fieldSquares = FieldSquares.getInstance();
        createMonsterballs();
        createTimeTickets();
        car = new Car(new Point2D.Float(SQUARE_LENGTH / 2 * SQUARE_UNITS, (SQUARE_LENGTH - 1) * SQUARE_UNITS), CAR_COLOR, 270, 50, SQUARE_UNITS, SQUARE_UNITS);
        state = State.getInstance();
    }

    /**
     * @return Size of the gameworld in pixels
     */
    public int getSize() {
        float squareSize = fieldSquares.elementAt(0, 0).getSize();
        return (int) (SQUARE_LENGTH * squareSize);
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
     * Updates view and state with proper information
     * @param delta Delta time since previous frame
     */
    public void update(float delta) {
        if (!state.isGameOver()) {
            state.addClock(-delta);

            MonsterBallCollision monsterCollision = new MonsterBallCollision();
            monsterCollision.actionPerformed(new ActionEvent(delta, 0, "MonsterBallCollision"));

            TimeTicketCollision ticketCollision = new TimeTicketCollision();
            ticketCollision.actionPerformed(new ActionEvent(this, 0, "TimeTicketCollision"));

            car.changeLocation(fieldSquares, delta, state);
        }
    }

    /**
     * Resets fieldSquares, monsterBalls, timeTickets, car and state
     */
    void reset() {
        this.fieldSquares.reset();
        createMonsterballs();
        createTimeTickets();
        this.car.reset();
        this.state.reset();
    }
}
