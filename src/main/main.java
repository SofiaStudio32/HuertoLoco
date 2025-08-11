package main;

import model.MainModel;
import view.MainView;
import controller.MainController;

public class Main {
    public static void main(String[] args) {
        // Crear instancias del modelo y la vista
        MainModel model = new MainModel();
        MainView view = new MainView();

        // Crear el controlador y conectar modelo y vista
        MainController controller = new MainController(model, view);
    }
}