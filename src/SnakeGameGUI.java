import java.awt.Color;

import javax.swing.*;

public class SnakeGameGUI extends JFrame
        implements StartScreenListener, GameOverListener, EndScreenListener {
    private StartScreenGUI startScreen;
    private GamePanelGUI gamePanel;

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

    @Override
    public void onCountdownFinished() {
        remove(startScreen);
        gamePanel.setGameOverListener(this);
        startNewGame();
        // add(gamePanel);

        // gamePanel.setBackground(new Color(0, 128, 128));
        // gamePanel.createBorderWalls(this);
        // gamePanel.requestFocusInWindow();

        // pack();
        // setVisible(true);
    }

    @Override
    public void onGameOver(int finalScore) {
         remove(gamePanel);
        EndScreenGUI endScreen = new EndScreenGUI(finalScore, this);
        add(endScreen);
        pack();
        setVisible(true);
    }

    @Override
    public void onRetry() {
        getContentPane().removeAll();
        revalidate();
        repaint();

        startNewGame();
    }

    @Override
    public void onExit() {
        System.exit(0);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnakeGameGUI();
        });
    }

}
