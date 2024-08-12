
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BonusMeal extends Meal implements ActionListener, MealType {
    private Timer blinkTimer;
    private boolean isVisible;
    private static final int BLINK_INTERVAL = 500;

    public BonusMeal(Point position) {
        super(position, 15);
        isVisible = true; 
        // Timer to handle blinking
        blinkTimer = new Timer(BLINK_INTERVAL, this);
        blinkTimer.start();
    }

    @Override
    public void applyEffect(Snake snake) {
        snake.grow(); // Grow by a larger amount (implement in Snake)
        blinkTimer.stop();
    }

    @Override
    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.BLUE);
            super.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Toggle visibility for blinking effect
        isVisible = !isVisible;
    }

    @Override
    public String getType() {
        return "bonusMeal";
    }
}

