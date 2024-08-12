import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Snake class represents a snake in the game with functionality to move, grow, and manage
 * lives.
 */
public class Snake {
    private final ArrayList<Point> body;
    private final Lock directionLock = new ReentrantLock(); // Lock for thread safety
    private int direction;
    private boolean growing;
    private int lives;

    public static final int DEFAULT_LIVES = 3;
    public static final int STEP_SIZE = 10; // Movement step size

    /**
     * Constructs a Snake with an initial position, direction, and lives.
     */
    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(40, 40));
        direction = KeyEvent.VK_RIGHT;
        growing = false;
        lives = DEFAULT_LIVES;
    }

    /**
     * Updates the snake's position based on its current direction.
     * The snake moves forward, and if it's not growing, the last segment is
     * removed.
     */
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

    /**
     * Draws the snake on the specified graphics context.
     *
     * @param g the graphics context on which to draw the snake
     */
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point p : body) {
            g.fillRect(p.x, p.y, STEP_SIZE, STEP_SIZE);
        }
    }

    /**
     * Changes the direction of the snake if the new direction is valid (i.e., not
     * directly opposite).
     *
     * @param keyCode the key code representing the new direction
     */
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

    /**
     * Returns the current position of the snake's head.
     *
     * @return the current position of the snake's head
     */
    public Point getHead() {
        return body.get(0);
    }

    /**
     * Returns the number of lives the snake has remaining.
     *
     * @return the number of lives remaining
     */
    public int getLives() {
        return lives;
    }

    /**
     * Returns an unmodifiable list of points representing the body segments of the
     * snake, excluding the head.
     *
     * @return a list of points representing the snake's body segments
     */
    public List<Point> getBody() {
        return Collections.unmodifiableList(body.subList(1, body.size()));
    }

    /**
     * Sets the snake to grow by one segment on the next update.
     */
    public void grow() {
        growing = true;
    }

    /**
     * Decreases the number of lives the snake has by one.
     */
    public void decreaseLife() {
        lives--;
    }

    /**
     * Decreases the length of the snake by removing a specified number of segments
     * from the end.
     *
     * @param amount the number of segments to remove
     */
    public void decreaseLength(int amount) {
        for (int i = 0; i < amount; i++) {
            if (body.size() > 1) {
                body.remove(body.size() - 1);
            }
        }
    }

    /**
     * Increases the number of lives the snake has by one.
     */
    public void increaseLife() {
        lives++;
    }
}
