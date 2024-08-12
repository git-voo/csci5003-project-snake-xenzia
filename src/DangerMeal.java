 
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangerMeal extends Meal implements ActionListener {
 
    private Timer blinkTimer;
    private boolean isVisible;
    private static final int BLINK_INTERVAL = 500;

    public DangerMeal(Point position) {
        super(position, 15);
        isVisible = true; 
        // Timer to handle blinking
        blinkTimer = new Timer(BLINK_INTERVAL, this);
        blinkTimer.start();
    }

    @Override
    public void applyEffect(Snake snake) {
        snake.decreaseLife();  
        blinkTimer.stop();
    }

    @Override
    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.RED);
            super.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Toggle visibility for blinking effect
        isVisible = !isVisible;
    }
}