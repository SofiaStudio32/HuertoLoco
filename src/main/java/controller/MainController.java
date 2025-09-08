package controller;

import model.MainModel;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainModel model;
    private MainView view;

    public MainController(MainModel model, MainView view) {
        this.model = model;
        this.view = view;

        this.view.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });
    }

    private void handleUserInput() {
        String input = view.getInput();
        // Aquí podrías procesar el input con el modelo, por ejemplo:
        model.setPlantDetails(input, 1); // ejemplo simple
        view.setOutput("Planta registrada: " + model.getPlantName());
    }
}