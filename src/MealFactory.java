import java.awt.*;
import java.util.Random;

/**
 * The MealFactory class is responsible for creating different types of Meal
 * objects
 * based on a specified type and position.
 */
public class MealFactory {
    private static final Random random = new Random();

    /**
     * Creates a Meal object based on the provided type and position.
     *
     * @param type     The type of Meal to create.
     * @param position The position where the Meal will be placed on the game board.
     * @return A Meal object corresponding to the specified type. A regular meal is
     *         retured by default if an unrecogniged meal type is provided.
     * 
     */
    public Meal create(String type, Point position) {
        switch (type) {
            case "regularMeal":
                return new RegularMeal(position);
            case "bonusMeal":
                return new BonusMeal(position);
            case "dangerMeal":
                return new DangerMeal(position);
            case "healthPack":
                return new HealthPack(position);
            default:
                return new RegularMeal(position);
        }
    }
}
