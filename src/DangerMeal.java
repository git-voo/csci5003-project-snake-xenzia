
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DangerMeal class represents a special meal in the snake game which when
 * eaten by the snake,
 * reduces its life . it also blinks on the screen to add a viual effect.
 */

public class DangerMeal extends Meal implements ActionListener, MealType {

    private Timer blinkTimer;
    private boolean isVisible;
    private static final int BLINK_INTERVAL = 500;

    /**
     * Contructs a DangerMeal object at the specified position with a blinking
     * effect.
     * 
     * @param position the position where the DangerMeal should appear on the game
     *                 board.
     */

    public DangerMeal(Point position) {
        super(position, 15);
        isVisible = true;
        blinkTimer = new Timer(BLINK_INTERVAL, this);
        blinkTimer.start();
    }

    /**
     * Applies the effect of the DangerMeal to the snake, by decreasing its life.
     * This is implemented in the Snake class
     * Stops the blinking effect once the DangerMeal is eaten by the snake.
     * 
     * @param snake The snake that consumes the BonusMeal.
     */
    @Override
    public void applyEffect(Snake snake) {
        snake.decreaseLife();
        blinkTimer.stop();
    }

    /**
     * Draws the DangerMeal on the screen as a red, blinking rectangle.
     *
     * @param g The Graphics object used for drawing.
     */

    @Override
    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.RED);
            super.draw(g);
        }
    }

    /**
     * Toggles the visibility of the DangerMeal for the blinking effect.
     * 
     * @param e The action event triggered by the blink timer.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        // Toggle visibility for blinking effect
        isVisible = !isVisible;
    }

    /**
     * Returns the type of this meal as a string.
     * 
     * @return The string "DangerMeal".
     */

    @Override
    public String getType() {
        return "dangerMeal";
    }
}