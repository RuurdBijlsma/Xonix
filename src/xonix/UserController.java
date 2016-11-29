package xonix;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.Random;

class UserController {
    private GameWorld model;
    private Random random = new Random();

    UserController(GameView view, GameWorld model) {
        this.model = model;

        view.addKeyListener(new KeyListener() {
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
    }

    /**
     * Handles key events
     *
     * @param keycode code of pressed key
     */
    private void execute(int keycode) {
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                model.car.setHeading(180);
                break;
            case KeyEvent.VK_UP:
                model.car.setHeading(90);
                break;
            case KeyEvent.VK_RIGHT:
                model.car.setHeading(0);
                break;
            case KeyEvent.VK_DOWN:
                model.car.setHeading(270);
                break;
            case KeyEvent.VK_SPACE:
                if (model.state.isGameOver())
                    model.reset();
                break;
            case KeyEvent.VK_I:
                model.car.setSpeed(model.car.getSpeed() + 5);
                break;
            case KeyEvent.VK_K:
                model.timeTickets.add(new TimeTicket(new Point2D.Float(random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15), GameWorld.TICKET_COLOR, GameWorld.TIME_START, 7, 7));
                break;
            case KeyEvent.VK_L:
                model.car.setSpeed(model.car.getSpeed() - 5);
                break;
            case KeyEvent.VK_M:
                model.monsterBalls.add(new MonsterBall(new Point2D.Float(random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15, random.nextInt(GameWorld.SQUARE_LENGTH * GameWorld.SQUARE_UNITS - 30) + 15), GameWorld.MONSTER_COLOR, random.nextInt(360), random.nextFloat() * 100 + 10, 6));
                break;
        }
    }
}
