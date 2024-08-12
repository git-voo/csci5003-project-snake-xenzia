import java.awt.*;

public  class Meal extends MealAction{
    protected Point position;
    protected int size;

    public Meal(Point position, int size) {
        super();
        this.position = position;
        this.size = size;
    }


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
