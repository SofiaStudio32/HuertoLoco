package main;

import controller.GameController;
import model.FarmersModel;
import model.QuestionsModel;

public class Main {
    public static void main(String[] args) {
        FarmersModel farmersModel = new FarmersModel();
        QuestionsModel questionsModel = new QuestionsModel();

        new GameController(farmersModel.getFarmers(), questionsModel.getQuestions());
    }
}