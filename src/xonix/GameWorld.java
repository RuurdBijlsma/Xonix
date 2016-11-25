package xonix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;


public class GameWorld {

    static final int SQUARE_LENGTH = 102;
    static final int SQUARE_UNITS = 5;
    static final int GAME_TICK_DELAY = 40;
    //    static final Color NO_COLOR = Color.white;
    static final Color CAR_COLOR = Color.red;
    static final Color SQUARE_COLOR = Color.black;
    static final Color LINE_COLOR = Color.red.darker().darker();
    static final Color PLAYER_COLOR = Color.cyan;
    private final Color monsterColor = Color.orange;
    private final Color ticketColor = Color.green;
    private final int LEVEL_START = 1;
    //    static final int CLOCK_START = (6 - LEVEL_START) * 2;
//    static final int LIVES_START = 3;
//    static final int CSCORE_START = 0;
//    static final int RSCORE_START = (40 + LEVEL_START * 10) * 100;
    private final int TIME_START = 55 - LEVEL_START;

    private final GameView gv;
    public final FieldSquares fss;
    public final Car car;
    private final Random random;
    public ArrayList<MonsterBall> mbs;
    public ArrayList<TimeTicket> tts;
    public final State state;

    GameWorld() {
        this.random = new Random();
        this.gv = new GameView();
        this.gv.setWorld(this);
        this.fss = new FieldSquares();
        createMonsterballs();
        createTimeTickets();
        this.car = new Car(new Point2D.Float(SQUARE_LENGTH / 2 * SQUARE_UNITS, (SQUARE_LENGTH - 1) * SQUARE_UNITS), CAR_COLOR, 270, 50, SQUARE_UNITS, SQUARE_UNITS);
        this.state = new State();
        gv.addKeyListener(new KeyListener() {
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

    private void createMonsterballs() {
        this.mbs = new ArrayList<>();
        int number = random.nextInt(10) + 1;
        for (int i = 0; i < number; i++)
            mbs.add(new MonsterBall(new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), monsterColor, random.nextInt(360), random.nextFloat() * 100 + 10, 6));
    }

    private void createTimeTickets() {
        this.tts = new ArrayList<>();
        int number = random.nextInt(SQUARE_UNITS) + 1;
        for (int i = 0; i < number; i++)
            tts.add(new TimeTicket(new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), ticketColor, TIME_START, 7, 7));
    }

    private void play() {
        gv.score.update();
        new Timer(GAME_TICK_DELAY, evt -> update((float) (GAME_TICK_DELAY / 1000.0))).start();
    }

    private void update(float delta) {
        if (!state.isGameOver()) {
            state.addClock(-delta);
            for (MonsterBall mb : mbs)
                if (mb.changeLocation(fss, delta, null)) {
                    state.decLives();
                    mbs.remove(mb);
                    break;
                }
            car.changeLocation(fss, delta, state);
            for (TimeTicket tt : tts)
                if (tt.contains(car.getLocation())) {
                    state.setClock(state.getClock() + tt.getSeconds());
                    tts.remove(tt);
                    gv.score.update();
                    break;
                }
        }
        gv.update();
    }

    private void reset() {
        this.fss.reset();
        createMonsterballs();
        createTimeTickets();
        this.car.reset();
        this.state.reset();
    }

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
                tts.add(new TimeTicket(new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), ticketColor, TIME_START, 7, 7));
                break;
            case KeyEvent.VK_L:
                car.setSpeed(car.getSpeed() - 5);
                break;
            case KeyEvent.VK_M:
                mbs.add(new MonsterBall(new Point2D.Float(random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15, random.nextInt(SQUARE_LENGTH * SQUARE_UNITS - 30) + 15), monsterColor, random.nextInt(360), random.nextFloat() * 100 + 10, 6));
                break;
        }
    }
}
