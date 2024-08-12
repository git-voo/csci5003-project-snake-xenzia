/**
 * The MealAction class serves as a wrapper or decorator for Meal objects,
 * allowing the application of effects on a Snake and retrieval of the meal
 * type.
 */
public class MealAction {

   private Meal meal;

   /**
    * Default constructor for the MealAction class.
    */
   public MealAction() {
   }

   /**
    * Constructs a MealAction object with a specified Meal.
    *
    * @param meal The Meal object that this MealAction will manage.
    */
   public MealAction(Meal meal) {
      this.meal = meal;
   }

   /**
    * Applies the effect of the meal to the specified snake.
    *
    * @param snake The Snake object that will receive the meal's effect.
    */
   public void applyEffect(Snake snake) {
      meal.applyEffect(snake);
   }

   /**
    * Retrieves the type of the meal.
    *
    * @return A string representing the type of the meal.
    */
   public String getType() {
      return meal.getType();
   }
}
