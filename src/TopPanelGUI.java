import javax.swing.*;
import java.awt.*;

public class TopPanelGUI extends JPanel { 
    protected  final int DEFAULT_SCORE = 0;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private int score = DEFAULT_SCORE;
    private int lives;
    private int level;
    private JLabel levelLabel;

    public TopPanelGUI(Snake snake) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(800, 30));

        level = 1;
        lives = snake.getLives();

        scoreLabel = new JLabel("Score: " + score);
        livesLabel = new JLabel("Lives: ");
        levelLabel = new JLabel("Level: " + level);

        
        
        add(scoreLabel);
        add(levelLabel);
        add(livesLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHearts(g, lives);
    }

    public void updateScore(int score) {
        this.score = score;
        scoreLabel.setText("Score: " + score);
        repaint();
    }
    public int getLevel(){
        return this.level;
    }

    public void updateLevel(int level) {
        this.level = level;
        levelLabel.setText("Level: " + level);
        repaint();
    }

    public void updateLives(int lives) {
        this.lives = lives;
        repaint();
    }

    private void drawHearts(Graphics g, int numHearts) {
        int heartSize = 8;  
        int spacing = 10;    
        int x = livesLabel.getX() + livesLabel.getWidth() + 5; // Start after the "Lives:" label
        int y = 10;         // Vertical position for hearts

        for (int i = 0; i < numHearts; i++) {
            drawHeart(g, x, y, heartSize);
            x += heartSize + spacing;
        }
    }

    private void drawHeart(Graphics g, int x, int y, int size) {
        g.setColor(Color.RED);
        
        // Draw the left half of the heart
        g.fillArc(x, y, size, size, 0, 180);
        
        // Draw the right half of the heart
        g.fillArc(x + size, y, size, size, 0, 180);
        
        // Draw the bottom triangle of the heart
        g.fillPolygon(new int[] {x, x + size, x + 2 * size}, 
                      new int[] {y + size / 2, y + size + size / 2, y + size / 2}, 3);
    }

}