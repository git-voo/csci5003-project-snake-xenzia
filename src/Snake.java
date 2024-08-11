import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Snake {
    private final ArrayList<Point> body;
    private final Lock directionLock = new ReentrantLock(); // Lock for thread safety
    private int direction;
    private boolean growing;
    private int lives;
    
    public static final int DEFAULT_LIVES = 3;
    public static final int STEP_SIZE = 10; // Movement step size

    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(40, 40));
        direction = KeyEvent.VK_RIGHT;
        growing = false;
        lives = DEFAULT_LIVES;
    }

    public void update() {
        directionLock.lock();
        try {
            Point head = new Point(body.get(0));

            // Update head position based on direction
            switch (direction) {
                case KeyEvent.VK_UP:
                    head.y -= STEP_SIZE;
                    break;
                case KeyEvent.VK_DOWN:
                    head.y += STEP_SIZE;
                    break;
                case KeyEvent.VK_LEFT:
                    head.x -= STEP_SIZE;
                    break;
                case KeyEvent.VK_RIGHT:
                    head.x += STEP_SIZE;
                    break;
            }

            // Add new head to the body
            body.add(0, head);

            if (!growing) {
                body.remove(body.size() - 1);
            } else {
                growing = false;
            }
        } finally {
            directionLock.unlock();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point p : body) {
            g.fillRect(p.x, p.y, STEP_SIZE, STEP_SIZE);
        }
    }

    public void changeDirection(int keyCode) {
        directionLock.lock();
        try {
            if ((keyCode == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) ||
                (keyCode == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) ||
                (keyCode == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) ||
                (keyCode == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT)) {
                direction = keyCode;
            }
        } finally {
            directionLock.unlock();
        }
    }

    public Point getHead() {
        return body.get(0);
    }

    public int getLives() {
        return lives;
    }

    public List<Point> getBody() {
        return Collections.unmodifiableList(body.subList(1, body.size()));
    }

    public void grow() {
        growing = true;
    }

    public void decreaseLife() {
        lives--;
    }

    public void decreaseLength(int amount) {
        for (int i = 0; i < amount; i++) {
            if (body.size() > 1) {
                body.remove(body.size() - 1);
            }
        }
    }

    public void increaseLife() {
        lives++;
    }
}
