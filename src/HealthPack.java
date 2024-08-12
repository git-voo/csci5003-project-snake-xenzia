import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The HealthPack class represents a special meal in the Snake game that
 * increases the player's life when consumed. It blinks at regular intervals.
 */
public class HealthPack extends Meal implements ActionListener, MealType {

    private Timer blinkTimer;
    private boolean isVisible;
    private static final int BLINK_INTERVAL = 500;

    /**
     * Constructs a HealthPack object at the specified position with a default size.
     *
     * @param position The position where the health pack will appear.
     */
    public HealthPack(Point position) {
        super(position, 15);
        isVisible = true;
        blinkTimer = new Timer(BLINK_INTERVAL, this);
        blinkTimer.start();
    }

    /**
     * Applies the health pack effect to the snake, increasing its life and
     * decreasing its length.
     * 
     * @param snake The snake object that will receive the effect.
     */
    @Override
    public void applyEffect(Snake snake) {
        snake.increaseLife();
        snake.decreaseLength(1);
        blinkTimer.stop();
    }

    /**
     * Draws the health pack on the screen as a pink, blinking rectangle.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.PINK);
            super.draw(g);
        }
    }

    /**
     * Toggles the visibility of the health pack for a blinking effect.
     *
     * @param e The ActionEvent triggered by the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        isVisible = !isVisible;
    }

    /**
     * Returns the type of this meal as a string.
     *
     * @return the string "healthPack".
     */
    @Override
    public String getType() {
        return "healthPack";
    }
}
