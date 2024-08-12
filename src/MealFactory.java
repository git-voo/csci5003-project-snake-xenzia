
import java.awt.*;
import java.util.Random;

public class MealFactory {
    private static final Random random = new Random();

    public static Meal createMeal(int type) {
        Point position = new Point(random.nextInt(800), random.nextInt(600));

        switch (type) {
            case 0: return new RegularMeal(position);
            case 1: return new BonusMeal(position);
            case 2: return new DangerMeal(position);
            case 3: return new HealthPack(position);
            default: return new RegularMeal(position);
        }
    }
}
