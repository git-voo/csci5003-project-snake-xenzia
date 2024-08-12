import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreenGUI extends JPanel {
    private JButton playButton;
    private int countdown = 4;
    private Timer countdownTimer;
    private StartScreenListener listener;
    private boolean countdownStarted = false;

    public StartScreenGUI(StartScreenListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());

        playButton = new JButton("Play Game");
        playButton.setFont(new Font("Arial", Font.BOLD, 30));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCountdown();
            }
        });

        add(playButton, BorderLayout.CENTER);
    }

    private void startCountdown() {
        playButton.setEnabled(false);
        countdownStarted = true;
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                if (countdown > 0) {
                    countdown--;
                } else {
                    countdownTimer.stop();
                    listener.onCountdownFinished();
                }
            }
        });
        countdownTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        playButton.setVisible(!countdownStarted);

        if (countdownStarted) {
            g.setFont(new Font("Arial", Font.BOLD, 100));
            g.setColor(Color.RED);
            String countdownText = countdown > 0 ? String.valueOf(countdown) : "Go!";
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(countdownText);
            int panelWidth = (getWidth() - textWidth) / 2;
            int panelHeight = getHeight() / 2 + fm.getAscent() / 2;
            g.drawString(countdownText, panelWidth, panelHeight);
        }
    }


}