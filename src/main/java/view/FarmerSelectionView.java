package view;

import model.Farmer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FarmerSelectionView {
    private JFrame frame;
    private JButton[] farmerButtons;

    public FarmerSelectionView(Farmer[] farmers) {
        frame = new JFrame("Selecciona tu granjero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new QuestionView.FarmBackgroundPanel());
        frame.setLayout(new GridBagLayout());
        farmerButtons = new JButton[farmers.length];

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        for (int i = 0; i < farmers.length; i++) {
            Farmer farmer = farmers[i];
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(180, 220));

            // Avatar animado (simple: sonriente)
            JLabel avatar = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Cabeza
                    g.setColor(farmer.getColor());
                    g.fillOval(20, 10, 80, 80);
                    // Ojos
                    g.setColor(Color.BLACK);
                    g.fillOval(45, 35, 10, 10);
                    g.fillOval(75, 35, 10, 10);
                    // Boca sonriente
                    g.setColor(Color.BLACK);
                    g.drawArc(50, 55, 30, 20, 0, -180);
                }
            };
            avatar.setPreferredSize(new Dimension(120, 100));
            panel.add(avatar, BorderLayout.NORTH);

            JLabel nameLabel = new JLabel(farmer.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            panel.add(nameLabel, BorderLayout.CENTER);

            JLabel descLabel = new JLabel("<html><center>" + farmer.getDescription() + "</center></html>", SwingConstants.CENTER);
            descLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            descLabel.setForeground(new Color(60, 60, 60));
            panel.add(descLabel, BorderLayout.SOUTH);

            farmerButtons[i] = new JButton();
            farmerButtons[i].setLayout(new BorderLayout());
            farmerButtons[i].add(panel, BorderLayout.CENTER);
            farmerButtons[i].setPreferredSize(new Dimension(180, 220));
            farmerButtons[i].setFocusPainted(false);
            farmerButtons[i].setBackground(new Color(255, 255, 255, 180));
            farmerButtons[i].setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2, true));

            gbc.gridx = i;
            mainPanel.add(farmerButtons[i], gbc);
        }

        frame.add(mainPanel);
        frame.setSize(900, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addFarmerListener(int index, ActionListener listener) {
        farmerButtons[index].addActionListener(listener);
    }

    public void close() {
        frame.dispose();
    }
}