package view;
    
import javax.swing.*;

/**
 * Clase para manejar la ventana principal de la aplicación.
 * Esta clase crea una ventana JFrame que se puede utilizar en diferentes vistas.
 */

public class AppFrame {
    public static final JFrame frame = new JFrame("Huerto Loco");

    static {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800); // Tamaño ventana grande y cómoda
        frame.setLocationRelativeTo(null); // Centrado en pantalla
        frame.setVisible(true);
    }
    /**
     * Método para obtener la instancia del JFrame.
     * @return JFrame de la aplicación.
     */
}
