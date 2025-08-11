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

        this.view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });
    }

    private void handleUserInput() {
        String input = view.getUserInput();
        model.processInput(input);
        view.updateDisplay(model.getData());
    }
}