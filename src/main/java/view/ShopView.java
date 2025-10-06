package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista de la tienda de poderes.
 * Permite al usuario ver sus puntos, comprar poderes y visualizar cuántos tiene de cada tipo.
 */
public class ShopView {
    // --- Ventana principal ---
    private JFrame frame;
    // --- Label para mostrar puntos disponibles ---
    private JLabel pointsLabel;
    // --- Botones para comprar cada poder ---
    private JButton[] powerButtons;
    // --- Labels para mostrar nombre, costo y cantidad de cada poder ---
    private JLabel[] powerLabels;
    // --- Botón para cerrar la tienda ---
    private JButton closeButton;

    /**
     * Constructor. Inicializa la ventana de tienda y sus componentes.
     * @param powers Nombres de los poderes disponibles.
     * @param costs Costos de cada poder en puntos.
     * @param powerCounts Cantidad actual de cada poder.
     * @param currentPoints Puntos disponibles del usuario.
     */
    public ShopView(String[] powers, int[] costs, int[] powerCounts, int currentPoints) {
        frame = new JFrame("Tienda de Poderes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(new QuestionView.FarmBackgroundPanel());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);

        // --- Label de puntos disponibles ---
        pointsLabel = new JLabel("Puntos disponibles: " + currentPoints, SwingConstants.CENTER);
        pointsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        pointsLabel.setForeground(new Color(34, 139, 34));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        mainPanel.add(pointsLabel, gbc);

        powerButtons = new JButton[powers.length];
        powerLabels = new JLabel[powers.length];

        gbc.gridwidth = 1;
        for (int i = 0; i < powers.length; i++) {
            // --- Label para nombre, costo y cantidad de cada poder ---
            powerLabels[i] = new JLabel(
                powers[i] + " (" + costs[i] + " pts) - Tienes: " + powerCounts[i],
                SwingConstants.LEFT
            );
            powerLabels[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
            gbc.gridx = 0; gbc.gridy = i + 1;
            mainPanel.add(powerLabels[i], gbc);

            // --- Botón para comprar el poder ---
            powerButtons[i] = new JButton("Comprar");
            powerButtons[i].setFont(new Font("Comic Sans MS", Font.BOLD, 14));
            powerButtons[i].setBackground(new Color(255, 236, 179));
            gbc.gridx = 1;
            mainPanel.add(powerButtons[i], gbc);
        }

        // --- Botón para cerrar la tienda ---
        closeButton = new JButton("Cerrar");
        closeButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        closeButton.setBackground(new Color(255, 236, 179));
        gbc.gridx = 0; gbc.gridy = powers.length + 2; gbc.gridwidth = 2;
        mainPanel.add(closeButton, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Actualiza el label de puntos disponibles.
     * @param points Puntos actuales del usuario.
     */
    public void setPoints(int points) {
        pointsLabel.setText("Puntos disponibles: " + points);
    }

    /**
     * Asocia un listener al botón de compra de cada poder.
     * @param index Índice del poder.
     * @param listener Acción a ejecutar al comprar el poder.
     */
    public void addPowerListener(int index, ActionListener listener) {
        powerButtons[index].addActionListener(listener);
    }

    /**
     * Asocia un listener al botón de cerrar tienda.
     * @param listener Acción a ejecutar al cerrar la tienda.
     */
    public void addCloseListener(ActionListener listener) {
        closeButton.addActionListener(listener);
    }

    /**
     * Muestra un mensaje emergente al usuario.
     * @param message Mensaje a mostrar.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * Cierra la ventana de la tienda.
     */
    public void close() {
        frame.dispose();
    }

    /**
     * Actualiza la cantidad de poderes en los labels de la tienda.
     * @param powerCounts Arreglo con la cantidad actual de cada poder.
     */
    public void setPowerCounts(int[] powerCounts) {
        for (int i = 0; i < powerLabels.length; i++) {
            String text = powerLabels[i].getText();
            // Actualiza solo la parte final
            int idx = text.lastIndexOf(" - Tienes:");
            if (idx != -1) {
                text = text.substring(0, idx);
            }
            powerLabels[i].setText(text + " - Tienes: " + powerCounts[i]);
        }
    }
}
