import java.awt.Color;
import javax.swing.*;

/**
 * The main GUI class for the Snake Xenzia game, handling the start screen, game
 * panel, and end screen.
 * Implements StartScreenListener, GameOverListener, and EndScreenListener
 * interfaces to handle various game events.
 */
public class SnakeGameGUI extends JFrame
        implements StartScreenListener, GameOverListener, EndScreenListener {
    private StartScreenGUI startScreen;
    private GamePanelGUI gamePanel;

    /**
     * Constructs the main game window, sets up the start screen, and makes the
     * window visible.
     */
    public SnakeGameGUI() {
        setTitle("Snake Xenzia");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        startScreen = new StartScreenGUI(this);
        gamePanel = new GamePanelGUI();

        add(startScreen);
        setVisible(true);
    }

    /**
     * Called when the countdown on the start screen finishes.
     * Removes the start screen, sets up the game panel, and starts a new game.
     */
    @Override
    public void onCountdownFinished() {
        remove(startScreen);
        gamePanel.setGameOverListener(this);
        startNewGame();
    }

    /**
     * Called when the game is over.
     * Removes the game panel, displays the end screen with the final score, and
     * updates the window.
     *
     * @param finalScore the final score of the game to be displayed on the end
     *                   screen
     */
    @Override
    public void onGameOver(int finalScore) {
        remove(gamePanel);
        EndScreenGUI endScreen = new EndScreenGUI(finalScore, this);
        add(endScreen);
        pack();
        setVisible(true);
    }

    /**
     * Called when the retry button is pressed on the end screen.
     * Clears the current content, starts a new game, and updates the window.
     */
    @Override
    public void onRetry() {
        getContentPane().removeAll();
        revalidate();
        repaint();

        startNewGame();
    }

    /**
     * Called when the exit button is pressed on the end screen.
     * Exits the application.
     */
    @Override
    public void onExit() {
        System.exit(0);
    }

    /**
     * Initializes a new game by creating a new GamePanelGUI, setting it up, and
     * adding it to the window.
     */
    private void startNewGame() {
        gamePanel = new GamePanelGUI();
        gamePanel.setGameOverListener(this);
        add(gamePanel);
        pack();
        setVisible(true);
        gamePanel.setBackground(new Color(0, 128, 128));
        gamePanel.createBorderWalls(this);
        gamePanel.requestFocusInWindow();
    }

    /**
     * The main entry point for the application.
     * Launches the SnakeGameGUI in the Event Dispatch Thread.
     *
     * @param args command-line arguments which in this case is not used
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnakeGameGUI();
        });
    }
}
