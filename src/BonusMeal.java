
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The BonusMeal class represents a special meal in the snake game which when
 * eaten by the snake,
 * increases the length of the snake by a significant amonth and also increases
 * the score of the
 * player by a significant amount also. it also blinks on the screen to add a
 * viual effect.
 */

public class BonusMeal extends Meal implements ActionListener, MealType {
    private Timer blinkTimer;
    private boolean isVisible;
    private static final int BLINK_INTERVAL = 500;

    /**
     * Contructs a BonusMeal object at the specified position with a blinking
     * effect.
     * 
     * @param position the position where the BonusMeal should appear on the game
     *                 board.
     */

    public BonusMeal(Point position) {
        super(position, 15);
        isVisible = true;
        // Timer to handle blinking
        blinkTimer = new Timer(BLINK_INTERVAL, this);
        blinkTimer.start();
    }

    /**
     * Applies the effect of the BonusMeal to the snake, making it grow by a larger
     * amount.
     * This is implemented in the Snake class
     * Stops the blinking effect once the BonusMeal is eaten by the snake.
     * 
     * @param snake The snake that consumes the BonusMeal.
     */

    @Override
    public void applyEffect(Snake snake) {
        snake.grow(); // Grow by a larger amount (implement in Snake)
        blinkTimer.stop();
    }

    /**
     * Draws the Bonus meal on the screen as a blue, blinking rectangle.
     * 
     * @param g The Graphics object used for drawing the BonusMeal.
     */

    @Override
    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.BLUE);
            super.draw(g);
        }
    }

    /**
     * Toggles the visibility of the BonusMeal for the blinking effect.
     * 
     * @param e The action event triggered by the blink timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        isVisible = !isVisible;
    }

    /**
     * Returns the type of this meal as a string.
     * 
     * @return The string "bonusMeal".
     */

    @Override
    public String getType() {
        return "bonusMeal";
    }
}
