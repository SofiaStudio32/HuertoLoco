package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista principal del menú de inicio de Huerto Loco.
 * Permite al usuario interactuar con el juego y acceder a la tienda de poderes.
 */
public class MainView {
    // --- Área de texto para mostrar información ---
    private JTextArea textArea;
    // --- Botón para enviar información ---
    private JButton button;
    // --- Botón para acceder a la tienda ---
    private JButton shopButton;

    /**
     * Constructor. Inicializa la ventana principal y sus componentes.
     */
    public MainView() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea(20, 40);
        button = new JButton("Submit");
        shopButton = new JButton("Tienda");

        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(button);
        southPanel.add(shopButton);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Usa el JFrame principal de la app
        AppFrame.frame.setContentPane(mainPanel);
        AppFrame.frame.setSize(1200, 800); // Tamaño ventana grande
        AppFrame.frame.setLocationRelativeTo(null);
        AppFrame.frame.revalidate();
        AppFrame.frame.repaint();
    }

    /**
     * Obtiene el texto ingresado por el usuario.
     * @return Texto actual del área de texto.
     */
    public String getInput() {
        return textArea.getText();
    }

    /**
     * Establece el texto a mostrar en el área de texto.
     * @param output Texto a mostrar.
     */
    public void setOutput(String output) {
        textArea.setText(output);
    }

    /**
     * Asocia un listener al botón de enviar información.
     * @param listener Acción a ejecutar al presionar "Submit".
     */
    public void addSubmitListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    /**
     * Asocia un listener al botón de la tienda.
     * @param listener Acción a ejecutar al presionar "Tienda".
     */
    public void addShopListener(ActionListener listener) {
        shopButton.addActionListener(listener);
    }
}