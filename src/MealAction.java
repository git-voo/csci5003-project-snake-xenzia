public class MealAction {

   Meal meal;

   public MealAction() {
     
   }
   public MealAction(Meal meal) {
      this.meal = meal;
   }

   public void applyEffect(Snake snake) {
      meal.applyEffect(snake);
   }

   public String getType() {
      return meal.getType();

   }

}
