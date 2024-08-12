
import java.awt.*;
import java.util.Random;

public class MealFactory {
    private static final Random random = new Random();

    public  Meal create(String type, Point position) {
        // Point position = new Point(random.nextInt(800), random.nextInt(600));

        switch (type) {
            case "regularMeal": return new RegularMeal(position);
            case "bonusMeal": return new BonusMeal(position);
            case "dangerMeal": return new DangerMeal(position);
            case "healthPack": return new HealthPack(position);
            default: return new RegularMeal(position);
        }
    }

    
}
