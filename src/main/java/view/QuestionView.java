package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Vista para mostrar preguntas, opciones de respuesta y poderes/comodines disponibles.
 * Incluye fondo animado tipo granja y feedback visual.
 */
public class QuestionView {
    // --- Emoji/avatar superior ---
    private JLabel emojiLabel;
    // --- Pregunta actual ---
    private JLabel questionLabel;
    // --- Opciones de respuesta ---
    private JRadioButton[] optionButtons;
    private ButtonGroup group;
    // --- Botón para enviar respuesta ---
    private JButton submitButton;
    // --- Feedback visual ---
    private JLabel feedbackLabel;
    // --- Temporizador ---
    private JLabel timerLabel;
    // --- Panel y botones de poderes/comodines ---
    private JButton[] powerButtons;
    private JLabel[] powerLabels;
    private JPanel powersPanel;

    /**
     * Constructor. Inicializa la ventana y todos los componentes visuales.
     * @param numOptions Número de opciones de respuesta.
     */
    public QuestionView(int numOptions) {
        // Fondo tipo granja
        JPanel backgroundPanel = new FarmBackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Panel de poderes arriba
        powersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        powersPanel.setOpaque(false);

        // Panel principal de pregunta
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(139, 69, 19), 3, true),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        // --- Emoji y pregunta ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        emojiLabel = new JLabel("🌾🐔", SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        topPanel.add(emojiLabel, BorderLayout.NORTH);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        topPanel.add(questionLabel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- Opciones de respuesta ---
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
            // Efecto hover visual
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

        // --- Zona inferior: botón, feedback y temporizador ---
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

        // Agrega el panel de pregunta al centro del fondo
        backgroundPanel.add(powersPanel, BorderLayout.NORTH);
        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        // Usa el JFrame principal
        AppFrame.frame.setContentPane(backgroundPanel);
        AppFrame.frame.revalidate();
        AppFrame.frame.repaint();
    }

    /**
     * Establece la pregunta y las opciones de respuesta.
     */
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

    /**
     * Devuelve el índice de la opción seleccionada, o -1 si ninguna.
     */
    public int getSelectedOption() {
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) return i;
        }
        return -1;
    }

    /**
     * Asocia un listener al botón de enviar respuesta.
     */
    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    /**
     * Muestra feedback visual según si la respuesta fue correcta o incorrecta.
     */
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

    /**
     * Actualiza el texto del temporizador.
     */
    public void setTimerText(String text) {
        timerLabel.setText(text);
    }

    /**
     * Cierra la ventana de pregunta.
     */
    public void close() {
        // No cerrar el JFrame, solo cambiar la vista desde el controlador
        // Este método queda vacío para compatibilidad
        // AppFrame.frame.dispose(); // No hacer esto, o cambiar a otra vista
    }

    /**
     * Cambia el emoji/avatar según el estado de ánimo (feliz/triste).
     */
    public void setAvatarMood(boolean happy) {
        if (happy) {
            emojiLabel.setText("😊🌾");
        } else {
            emojiLabel.setText("😢🌾");
        }
    }

    /**
     * Muestra los poderes/comodines disponibles arriba de la pregunta.
     * @param powerNames Nombres de los poderes.
     * @param powerCounts Cantidad disponible de cada poder.
     * @param icons Iconos visuales (pueden ser emojis).
     */
    public void setPowers(String[] powerNames, int[] powerCounts, Icon[] icons) {
        powersPanel.removeAll();
        powerButtons = new JButton[powerNames.length];
        powerLabels = new JLabel[powerNames.length];

        for (int i = 0; i < powerNames.length; i++) {
            powerButtons[i] = new JButton();
            powerButtons[i].setIcon(icons[i]);
            powerButtons[i].setToolTipText(powerNames[i]);
            powerButtons[i].setEnabled(powerCounts[i] > 0);
            powerButtons[i].setPreferredSize(new Dimension(48, 48));
            powerButtons[i].setBackground(new Color(255, 236, 179));
            powerButtons[i].setFocusPainted(false);

            powerLabels[i] = new JLabel(powerNames[i] + ": " + powerCounts[i]);
            powerLabels[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            powerLabels[i].setForeground(powerCounts[i] > 0 ? new Color(34, 139, 34) : Color.GRAY);

            powersPanel.add(powerButtons[i]);
            powersPanel.add(powerLabels[i]);
        }
        powersPanel.revalidate();
        powersPanel.repaint();
    }

    /**
     * Asocia un listener a cada botón de poder/comodín.
     */
    public void addPowerListener(int index, ActionListener listener) {
        if (powerButtons != null && index < powerButtons.length) {
            powerButtons[index].addActionListener(listener);
        }
    }

    /**
     * Establece un botón de poder/comodín especial (por ejemplo, para un evento único).
     */
    public void setSpecialPowerButton(String label, Icon icon, ActionListener listener) {
        JButton specialButton = new JButton(label);
        specialButton.setIcon(icon);
        specialButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        specialButton.setBackground(new Color(255, 236, 179));
        specialButton.setFocusPainted(false);
        specialButton.addActionListener(listener);
        powersPanel.add(specialButton); // Lo agrega junto a los poderes normales
        powersPanel.revalidate();
        powersPanel.repaint();
    }

    /**
     * Utilidad para crear un icono a partir de un emoji.
     */
    private Icon emojiIcon(String emoji) {
        return new JLabel(emoji).getIcon();
    }

    /**
     * Panel de fondo tipo granja, dibujado con gráficos 2D.
     */
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

    /**
     * Muestra un mensaje emergente al usuario.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(AppFrame.frame, message);
    }
}