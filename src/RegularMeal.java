import java.awt.*;

public class RegularMeal extends Meal {
    public RegularMeal(Point position) {
        super(position, 10);
    }

    @Override
    public void applyEffect(Snake snake) {
        snake.grow();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        super.draw(g);
    }
}
