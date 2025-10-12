package controller;

import model.Farmer;
import model.Pointsmodel;
import view.ShopView;
import view.FarmerSelectionView;

/**
 * Controlador de la tienda de poderes.
 * Permite comprar poderes usando los puntos acumulados y actualiza la vista y el modelo.
 */
public class ShopController {
    // --- Modelo de puntos y poderes ---
    private Pointsmodel pointsModel;
    // --- Vista de la tienda ---
    private ShopView shopView;
    // --- Nombres y costos de los poderes ---
    private String[] powers;
    private int[] costs;
    private Farmer[] farmers; // <-- Nuevo campo
    private GameController gameController;

    /**
     * Constructor. Inicializa la tienda y conecta los botones de compra y cierre.
     * @param pointsModel Modelo que gestiona puntos y poderes del usuario.
     * @param farmers Arreglo de granjeros disponibles.
     * @param gameController Controlador del juego, para regresar a la selección de personaje.
     */
    public ShopController(Pointsmodel pointsModel, Farmer[] farmers, GameController gameController) {
        this.pointsModel = pointsModel;
        this.farmers = farmers; // <-- Guarda el arreglo
        this.gameController = gameController;

        powers = new String[] {
            "Tiempo extra", "Pista", "Doble puntaje", "Salto de pregunta"
        };
        costs = new int[] {
            10, 15, 20, 25
        };
        // Obtiene la cantidad actual de cada poder
        int[] powerCounts = {
            pointsModel.getPower("tiempo_extra"),
            pointsModel.getPower("pista"),
            pointsModel.getPower("doble_puntaje"),
            pointsModel.getPower("salto_pregunta")
        };
        // Inicializa la vista de la tienda
        shopView = new ShopView(powers, costs, powerCounts, pointsModel.getPoints());

        for (int i = 0; i < powers.length; i++) {
            int idx = i;
            shopView.addPowerListener(i, e -> buyPower(idx));
        }
        // Modifica aquí el listener del botón cerrar:
        shopView.addCloseListener(e -> {
            shopView.close(); // Solo para compatibilidad, no cierra el JFrame
            gameController.showFarmerSelection();
        });
    }

    /**
     * Lógica para comprar un poder.
     * Verifica si hay puntos suficientes, descuenta el costo, suma el poder y actualiza la vista.
     * @param index Índice del poder a comprar.
     */
    private void buyPower(int index) {
        int cost = costs[index];
        String powerKey = powers[index].toLowerCase().replace(" ", "_");
        if (pointsModel.getPoints() >= cost) {
            pointsModel.spendPoints(cost);      // Descuenta puntos
            pointsModel.addPower(powerKey);     // Suma el poder comprado
            shopView.setPoints(pointsModel.getPoints());
            // Actualiza la cantidad de poderes en la vista
            int[] powerCounts = {
                pointsModel.getPower("tiempo_extra"),
                pointsModel.getPower("pista"),
                pointsModel.getPower("doble_puntaje"),
                pointsModel.getPower("salto_pregunta")
            };
            shopView.setPowerCounts(powerCounts);
            shopView.showMessage("¡Has comprado: " + powers[index] + "!");
        } else {
            shopView.showMessage("No tienes suficientes puntos para comprar este poder.");
        }
    }
}