import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanelGUI extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Snake snake;
    private ArrayList<Meal> meals;
    // private ArrayList<Meal> specialMeals;
    private boolean regularMealPresent;
    private boolean bonusMealPresent;

    private boolean specialMealPresent;
    // Special meal variables (DangerMeal/HealthPack)
    private Meal specialMeal;
    private Timer specialMealTimer;
    private Timer specialMealLifetimeTimer;

    int mealCount = 0;
    int mealIndex = 0;

    // Counter variables for handling bonus meal disappearance
    final int SPECIAL_MEAL_LIFETIME = 10000;

    // Game Area Boundaries
    private ArrayList<Wall> walls;
    private boolean gameOver;

    // Score and Life variables
    TopPanelGUI topPanel;
    private int score;

    // Game levels
    private int lastLevelUpScore = 0;
    GameOverListener gameOverListener;

    public GamePanelGUI() {
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(this);
        snake = new Snake();

        topPanel = new TopPanelGUI(snake);
        add(topPanel);

        score = topPanel.DEFAULT_SCORE;

        meals = new ArrayList<Meal>();

        walls = new ArrayList<>();

        regularMealPresent = false;
        bonusMealPresent = false;
        specialMealPresent = false;
        gameOver = false;

        timer = new Timer(100, this);
        timer.start();

        addInitialMeal();

    }

    private void addInitialMeal() {
        meals.add(new RegularMeal(generateRandomPosition()));
        regularMealPresent = true;
        mealCount = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        for (Meal meal : meals) {
            meal.draw(g);
        }

        if (specialMealPresent && specialMeal != null) {
            specialMeal.draw(g);
        }

        g.setColor(Color.WHITE);
        for (Wall wall : walls) {
            wall.draw(g);
        }

    }

    protected void createBorderWalls(JFrame frame) {
        // Get the size of the content pane of the frame
        Dimension frameSize = frame.getContentPane().getSize();

        int panelWidth = frameSize.width;
        int panelHeight = frameSize.height;

        // Clear existing walls if any (in case of resizing)
        walls.clear();

        // Top wall
        walls.add(new Wall(new Point(0, 0), new Dimension(panelWidth, 10)));
        // Bottom wall
        walls.add(new Wall(new Point(0, panelHeight - 10), new Dimension(panelWidth, 10)));
        // Left wall
        walls.add(new Wall(new Point(0, 0), new Dimension(10, panelHeight)));
        // Right wall
        walls.add(new Wall(new Point(panelWidth - 10, 0), new Dimension(10, panelHeight)));
    }

    private void spawnMeals() {
        if (!regularMealPresent) {
            meals.add(new RegularMeal(generateRandomPosition()));
            regularMealPresent = true;
            mealCount++;
        }

        if (mealCount % 6 == 0 && !bonusMealPresent) {
            meals.add(new BonusMeal(generateRandomPosition()));
            bonusMealPresent = true;
            mealCount = 1;
        }

    }

    private void startSpecialMealTimer() {
        final int MEAL_INTERVAL = 25000; // 25 seconds

        specialMealTimer = new Timer(MEAL_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!specialMealPresent) {
                    Random random = new Random();
                    if (random.nextBoolean()) {
                        specialMeal = new HealthPack(generateRandomPosition());
                    } else {
                        specialMeal = new DangerMeal(generateRandomPosition());
                    }
                    specialMealPresent = true;
                    startSpecialMealLifetimeTimer();
                }
            }
        });
        specialMealTimer.start();
    }

    private void startSpecialMealLifetimeTimer() {

        specialMealLifetimeTimer = new Timer(SPECIAL_MEAL_LIFETIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialMealPresent = false;
                specialMeal = null; // Remove the special meal
                ((Timer) e.getSource()).stop(); // Stop the lifetime timer
            }
        });
        specialMealLifetimeTimer.setRepeats(false); // Ensure it only runs once
        specialMealLifetimeTimer.start();
    }

    private void hideBonusMealAfterTenSecs() {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i) instanceof BonusMeal) {
                mealIndex = i;
                break;
            }
        }

        Timer lifeTimer = new Timer(SPECIAL_MEAL_LIFETIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (meals.size() > mealIndex) {
                    meals.remove(mealIndex);
                    bonusMealPresent = false;
                }
                mealCount = 1;
                ((Timer) e.getSource()).stop();
            }
        });

        lifeTimer.start();

    }

    private Point generateRandomPosition() {
        Random random = new Random();
        int width = getWidth();
        int height = getHeight();
        int wallThickness = 20;

        // Ensure the dimensions are within the panel's size
        if (width <= 0 || height <= 0) {
            width = 800;
            height = 600;
        }

        // Adjust the available area to exclude walls
        int xRange = (width - 2 * wallThickness) / 10;
        int yRange = (height - 2 * wallThickness) / 10;

        // Generate random positions within the adjusted ranges
        int x = wallThickness + random.nextInt(xRange) * 10;
        int y = wallThickness + random.nextInt(yRange) * 10;

        return new Point(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            snake.update();
            spawnMeals();
            checkCollisions();
            checkLevelUp();

            if (snake.getLives() <= 0) {
                gameOver();
            }
            repaint();
        }
    }

    private void checkCollisionWithMeals() {
        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            if (snake.getHead().equals(meal.getPosition())) {
                meal.applyEffect(snake);
                if (meal instanceof RegularMeal) {
                    regularMealPresent = false;
                }
                if (meal instanceof BonusMeal) {
                    bonusMealPresent = false;
                }
                score += meal.getSize();
                topPanel.updateScore(score);
                meals.remove(i);
                break;
            }

        }
    }

    private void checkCollisionWithSpecialMeals() {
        if (specialMealPresent && specialMeal != null) {
            if (snake.getHead().equals(specialMeal.getPosition())) {
                specialMeal.applyEffect(snake);
                specialMealPresent = false;
                specialMeal = null;
                topPanel.updateLives(snake.getLives());
                if (specialMealLifetimeTimer != null) {
                    specialMealLifetimeTimer.stop();
                }
            }
        }
    }




    private void checkCollisions() {

        if (bonusMealPresent)
            hideBonusMealAfterTenSecs();
        if (specialMealPresent)
            // hideSpecialMealAfterTenSecs();

            checkCollisionWithSpecialMeals();
        checkCollisionWithMeals();

        checkCollisionsWithWalls();

        checkCollisionWithSelf();

    }

    
    private void checkCollisionWithSelf() {
        Point snakeHead = snake.getHead();
        List<Point> snakeBody = snake.getBody();

        for (Point bodyPoint : snakeBody) {
            if (snakeHead.equals(bodyPoint)) {
                gameOver();
                break;
            }
        }
    }
 
    private void checkCollisionsWithWalls() {
        for (Wall wall : walls) {
            if (wall.collidesWith(snake.getHead())) {
                gameOver();
                break;
            }
        }
    }

    private void checkLevelUp() { 
 
            if (score - lastLevelUpScore >= 60) {
                lastLevelUpScore = score;
                timer.setDelay((int) (timer.getDelay() * 0.9)); // Increase game speed by 10%
                topPanel.updateLevel(topPanel.getLevel() + 1); // Assuming level starts from 1 and increases by 1 every 100 points
                // topPanel.updateLevel(topPanel.getLevel() + 1);
            }
     
    }

    @Override
    public void keyPressed(KeyEvent e) {
        snake.changeDirection(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
