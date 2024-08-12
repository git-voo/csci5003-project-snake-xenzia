import java.awt.*;

public abstract class Meal implements ApplyEffect {
    protected Point position;
    protected int size;

    public Meal(Point position, int size) {
        this.position = position;
        this.size = size;
    }

    // public abstract void applyEffect(Snake snake);

    public void draw(Graphics g) {
        g.fillRect(position.x, position.y, size, size);
    }

    public int getSize() {
        return this.size;
    }

    public Point getPosition() {
        return position;
    }
}
