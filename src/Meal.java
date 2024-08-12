import java.awt.*;

/**
 * The Meal class represents a generic meal in the Snake game.
 * It is responsible for maintaining the position and size of the meal and
 * providing methods to draw it on the screen.
 */
public class Meal extends MealAction {
    protected Point position;
    protected int size;

    /**
     * Constructs a Meal object at the specified position with the size given.
     *
     * @param position The position where the meal will appear.
     * @param size     The size of the meal.
     */
    public Meal(Point position, int size) {
        super();
        this.position = position;
        this.size = size;
    }

    /**
     * Draws the meal on the screen as a filled rectangle.
     *
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        g.fillRect(position.x, position.y, size, size);
    }

    /**
     * Gets the size of the meal.
     *
     * @return The size of the meal.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the position of the meal.
     *
     * @return The position of the meal as a Point object.
     */
    public Point getPosition() {
        return position;
    }
}
