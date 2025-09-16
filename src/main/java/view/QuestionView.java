package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionView {
    private JFrame frame;
    private JLabel emojiLabel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup group;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private JLabel timerLabel;
    private JPanel mainPanel;

    public QuestionView(int numOptions) {
        frame = new JFrame("Pregunta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new FarmBackgroundPanel());
        frame.setLayout(new GridBagLayout());

        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 3, true),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        // Emoji y pregunta
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        emojiLabel = new JLabel("🌾🐔", SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        topPanel.add(emojiLabel, BorderLayout.NORTH);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        topPanel.add(questionLabel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Opciones
        JPanel optionsPanel = new JPanel(new GridLayout(numOptions, 1, 10, 10));
        optionsPanel.setOpaque(false);
        optionButtons = new JRadioButton[numOptions];
        group = new ButtonGroup();
        for (int i = 0; i < numOptions; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            optionButtons[i].setOpaque(false);
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // Efecto hover
            final int idx = i;
            optionButtons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!optionButtons[idx].isSelected())
                        optionButtons[idx].setBackground(new Color(255, 255, 200, 120));
                    optionButtons[idx].setOpaque(true);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!optionButtons[idx].isSelected())
                        optionButtons[idx].setOpaque(false);
                }
            });
            group.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        // Zona inferior
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setOpaque(false);
        submitButton = new JButton("Responder");
        submitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        submitButton.setBackground(new Color(255, 236, 179));
        submitButton.setFocusPainted(false);

        feedbackLabel = new JLabel(" ", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
        feedbackLabel.setForeground(new Color(34, 139, 34));

        timerLabel = new JLabel("Tiempo: 12", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        timerLabel.setForeground(new Color(139, 69, 19));

        southPanel.add(submitButton, BorderLayout.WEST);
        southPanel.add(feedbackLabel, BorderLayout.CENTER);
        southPanel.add(timerLabel, BorderLayout.EAST);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Centrar mainPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.NONE;
        frame.add(mainPanel, gbc);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setQuestion(String question, String[] options) {
        questionLabel.setText("<html><center>" + question + "</center></html>");
        for (int i = 0; i < options.length; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setForeground(Color.BLACK);
            optionButtons[i].setSelected(false);
            optionButtons[i].setOpaque(false);
        }
        feedbackLabel.setText(" ");
    }

    public int getSelectedOption() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) return i;
        }
        return -1;
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void setFeedback(String feedback, boolean correct, int correctIndex, int selectedIndex) {
        if (correct) {
            optionButtons[correctIndex].setForeground(new Color(34, 139, 34));
            optionButtons[correctIndex].setOpaque(true);
            optionButtons[correctIndex].setBackground(new Color(200, 255, 200));
            feedbackLabel.setText(feedback);
        } else {
            if (selectedIndex >= 0) {
                optionButtons[selectedIndex].setForeground(Color.RED);
                optionButtons[selectedIndex].setOpaque(true);
                optionButtons[selectedIndex].setBackground(new Color(255, 200, 200));
            }
            optionButtons[correctIndex].setForeground(new Color(34, 139, 34));
            optionButtons[correctIndex].setOpaque(true);
            optionButtons[correctIndex].setBackground(new Color(200, 255, 200));
            feedbackLabel.setText(feedback);
        }
    }

    public void setTimerText(String text) {
        timerLabel.setText(text);
    }

    public void close() {
        frame.dispose();
    }

    public void setAvatarMood(boolean happy) {
        if (happy) {
            emojiLabel.setText("😊🌾");
        } else {
            emojiLabel.setText("😢🌾");
        }
    }

    // Panel de fondo tipo granja
    static class FarmBackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Cielo
            GradientPaint sky = new GradientPaint(0, 0, new Color(135, 206, 250), 0, getHeight()/2, new Color(255, 255, 255));
            g2.setPaint(sky);
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Pasto
            g2.setColor(new Color(184, 225, 134));
            g2.fillRect(0, getHeight()/2, getWidth(), getHeight()/2);

            // Sol
            g2.setColor(new Color(255, 255, 102, 180));
            g2.fillOval(getWidth()-120, 20, 80, 80);

            // Valla simple
            g2.setColor(new Color(222, 184, 135));
            for (int i = 0; i < getWidth(); i += 40) {
                g2.fillRect(i, getHeight()/2+40, 10, 40);
            }
            g2.fillRect(0, getHeight()/2+60, getWidth(), 10);

            // Árbol simple
            g2.setColor(new Color(139, 69, 19));
            g2.fillRect(60, getHeight()/2-20, 15, 50);
            g2.setColor(new Color(34, 139, 34));
            g2.fillOval(45, getHeight()/2-40, 45, 45);

            // Gallina (círculo blanco y pico)
            g2.setColor(Color.WHITE);
            g2.fillOval(getWidth()-180, getHeight()/2+30, 30, 30);
            g2.setColor(Color.ORANGE);
            int[] xPico = {getWidth()-150, getWidth()-140, getWidth()-145};
            int[] yPico = {getHeight()/2+45, getHeight()/2+50, getHeight()/2+55};
            g2.fillPolygon(xPico, yPico, 3);
        }
    }
}