import java.awt.*;

/**
 * Represents a wall that can be drawn on the screen and checked for collisions.
 */
public class Wall {
    private Rectangle bounds;

    /**
     * Constructs a Wall object with the specified position and size.
     *
     * @param position the position of the wall's top-left corner
     * @param size     the dimensions of the wall
     */
    public Wall(Point position, Dimension size) {
        this.bounds = new Rectangle(position, size);
    }

    /**
     * Draws the wall on the specified graphics context.
     *
     * @param g the graphics context on which to draw the wall
     */
    public void draw(Graphics g) {
        // g.setColor(new Color(236, 217, 221));
        // g.setColor(Color.RED);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * Checks if the specified point collides with the wall.
     *
     * @param point the point to check for collision
     * @return true if the point is within the bounds of the wall; false otherwise
     */
    public boolean collidesWith(Point point) {
        return bounds.contains(point);
    }
}
