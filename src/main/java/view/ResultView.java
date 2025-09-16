package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResultView {
    private JFrame frame;
    private JLabel emojiLabel;
    private JLabel scoreLabel;
    private JButton playAgainButton;

    public ResultView() {
        frame = new JFrame("Resultado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new QuestionView.FarmBackgroundPanel());
        frame.setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 3, true),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        emojiLabel = new JLabel("🏆🌻", SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        mainPanel.add(emojiLabel, BorderLayout.NORTH);

        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        scoreLabel.setForeground(new Color(34, 139, 34));
        mainPanel.add(scoreLabel, BorderLayout.CENTER);

        playAgainButton = new JButton("Volver a jugar");
        playAgainButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        playAgainButton.setBackground(new Color(255, 236, 179));
        playAgainButton.setFocusPainted(false);
        mainPanel.add(playAgainButton, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        frame.add(mainPanel, gbc);

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setScore(int score) {
        scoreLabel.setText("¡Tu puntaje final es: " + score + "!");
    }

    public void addPlayAgainListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    public void close() {
        frame.dispose();
    }
}