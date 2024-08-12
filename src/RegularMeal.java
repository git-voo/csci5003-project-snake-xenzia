import java.awt.*;

/**
 * The RegularMeal class represents a basic meal in the game.
 * When consumed by the snake, it causes the snake to grow.
 */
public class RegularMeal extends Meal implements MealType {

    /**
     * Constructs a RegularMeal object at the specified position.
     *
     * @param position The position on the game board where the regular meal is
     *                 placed.
     */
    public RegularMeal(Point position) {
        super(position, 10);
    }

    /**
     * Applies the effect of the regular meal to the snake by making it grow in
     * length.
     *
     * @param snake The snake that consumes the meal.
     */
    @Override
    public void applyEffect(Snake snake) {
        snake.grow();
    }

    /**
     * Draws the regular meal on the game board as a black filled rectangle.
     *
     * @param g The Graphics object used to draw the meal.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        super.draw(g);
    }

    /**
     * Returns the type of the meal as a string.
     *
     * @return A string representing the type of the meal, which is "regularMeal".
     */
    @Override
    public String getType() {
        return "regularMeal";
    }
}
