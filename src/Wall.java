import java.awt.*;

public class Wall {
    private Rectangle bounds;

    public Wall(Point position, Dimension size) {
        this.bounds = new Rectangle(position, size);
    }

    public void draw(Graphics g) {
        // g.setColor(new Color(236, 217, 221));
        // g.setColor(Color.RED);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public boolean collidesWith(Point point) {
        return bounds.contains(point);
    }
}
