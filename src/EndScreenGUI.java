import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Jpanel that shows the end screen of the snake game,
 * displaying the final score of the player and gives option to retry or exit
 * the game
 */

public class EndScreenGUI extends JPanel {
    private int finalScore;
    private JButton exitButton;
    private JButton retryButton;
    private EndScreenListener listener;

    /**
     * Constructs an EndScreenGUI with the specified final score and listener.
     *
     * @param finalScore the final score to display on the end screen
     * @param listener   the listener to handle button actions
     */

    public EndScreenGUI(int finalScore, EndScreenListener listener) {
        this.finalScore = finalScore;
        this.listener = listener;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        JLabel scoreLabel = new JLabel("Final Score: " + finalScore, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
        gameOverLabel.setForeground(Color.RED);
        scoreLabel.setForeground(Color.WHITE);

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setOpaque(false);
        labelPanel.add(gameOverLabel);
        labelPanel.add(scoreLabel);

        add(labelPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.BOLD, 20));
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onRetry();
            }
        });
        buttonPanel.add(retryButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onExit();
            }
        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setBackground(new Color(0, 128, 128)); // Match background color of the game
    }

}
