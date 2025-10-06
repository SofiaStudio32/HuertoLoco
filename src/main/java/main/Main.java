package main;

import controller.GameController;
import model.FarmersModel;
import model.QuestionsModel;
import model.Pointsmodel;

public class Main {
    public static void main(String[] args) {
        FarmersModel farmersModel = new FarmersModel();
        QuestionsModel questionsModel = new QuestionsModel();
        Pointsmodel pointsModel = new Pointsmodel();

        new GameController(
            farmersModel.getFarmers(),
            questionsModel.getQuestions(),
            pointsModel
        );
    }
}