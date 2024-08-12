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
