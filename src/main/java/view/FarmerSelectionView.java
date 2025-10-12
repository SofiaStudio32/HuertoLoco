package view;

import model.Farmer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista para la selección de personaje (granjero).
 * Permite al usuario elegir su granjero y acceder a la tienda de poderes.
 */
public class FarmerSelectionView {
    // --- Botones para cada granjero ---
    private JButton[] farmerButtons;
    // --- Botón para acceder a la tienda ---
    private JButton shopButton;

    /**
     * Constructor. Inicializa la ventana de selección de granjero y la tienda.
     * @param farmers Arreglo de granjeros disponibles.
     */
    public FarmerSelectionView(Farmer[] farmers) {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);

        // --- Título superior ---
        JLabel titleLabel = new JLabel("¡Elige tu granjero favorito!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titleLabel.setForeground(new Color(139, 69, 19));
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = farmers.length;
        gbcTitle.insets = new Insets(10, 10, 30, 10);
        mainPanel.add(titleLabel, gbcTitle);

        // --- Botón para la tienda ---
        shopButton = new JButton("Ir a la Tienda");
        shopButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        shopButton.setBackground(new Color(255, 236, 179));
        shopButton.setFocusPainted(false);
        GridBagConstraints gbcShop = new GridBagConstraints();
        gbcShop.gridx = farmers.length - 1;
        gbcShop.gridy = 1;
        gbcShop.anchor = GridBagConstraints.NORTHEAST;
        gbcShop.insets = new Insets(0, 0, 10, 10);
        mainPanel.add(shopButton, gbcShop);

        // --- Botones para cada granjero ---
        farmerButtons = new JButton[farmers.length];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 20, 20, 20);

        for (int i = 0; i < farmers.length; i++) {
            Farmer farmer = farmers[i];
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.setLayout(new BorderLayout());
            panel.setPreferredSize(new Dimension(180, 220));

            // --- Avatar animado del granjero ---
            JLabel avatar = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(farmer.getColor());
                    g.fillOval(20, 10, 80, 80);
                    g.setColor(Color.BLACK);
                    g.fillOval(45, 35, 10, 10);
                    g.fillOval(75, 35, 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawArc(50, 55, 30, 20, 0, -180);
                }
            };
            avatar.setPreferredSize(new Dimension(120, 100));
            panel.add(avatar, BorderLayout.NORTH);

            // --- Nombre del granjero ---
            JLabel nameLabel = new JLabel(farmer.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            panel.add(nameLabel, BorderLayout.CENTER);

            // --- Descripción del granjero ---
            JLabel descLabel = new JLabel("<html><center>" + farmer.getDescription() + "</center></html>", SwingConstants.CENTER);
            descLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            descLabel.setForeground(new Color(60, 60, 60));
            panel.add(descLabel, BorderLayout.SOUTH);

            // --- Botón para seleccionar este granjero ---
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

        // --- Fondo tipo granja ---
        JPanel backgroundPanel = new QuestionView.FarmBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        // --- Usa el JFrame principal de la app ---
        AppFrame.frame.setContentPane(backgroundPanel);
        AppFrame.frame.setSize(1200, 800); // Tamaño ventana grande
        AppFrame.frame.setLocationRelativeTo(null);
        AppFrame.frame.revalidate();
        AppFrame.frame.repaint();
    }

    /**
     * Asocia un listener al botón de cada granjero.
     * @param index Índice del granjero.
     * @param listener Acción a ejecutar al seleccionar el granjero.
     */
    public void addFarmerListener(int index, ActionListener listener) {
        farmerButtons[index].addActionListener(listener);
    }

    /**
     * Asocia un listener al botón de la tienda.
     * @param listener Acción a ejecutar al abrir la tienda.
     */
    public void addShopListener(ActionListener listener) {
        shopButton.addActionListener(listener);
    }

    /**
     * Cierra la vista (no hace nada, solo para compatibilidad).
     */
    public void close() {
        // No cerrar el JFrame, solo cambiar la vista desde el controlador
    }
}