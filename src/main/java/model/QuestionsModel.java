package model;

public class QuestionsModel {
    private Question[] questions;

    public QuestionsModel() {
        questions = new Question[] {
            new Question("¿Cuál es la probabilidad de sacar un 6 en un dado?", new String[]{"1/6", "1/3", "1/2", "1/4"}, 0),
            new Question("Si tienes 3 manzanas y tomas 1, ¿cuántas quedan?", new String[]{"1", "2", "3", "0"}, 1),
            new Question("¿Cuántos lados tiene un dado tradicional?", new String[]{"4", "6", "8", "12"}, 1),
            new Question("¿Cuál es la probabilidad de sacar una moneda y que salga cara?", new String[]{"1/2", "1/3", "1/4", "1"}, 0),
            new Question("Si tienes 10 semillas y plantas 4, ¿cuántas quedan?", new String[]{"4", "6", "10", "14"}, 1),
            new Question("¿Cuál es la probabilidad de sacar una carta roja de una baraja española?", new String[]{"1/2", "1/4", "1/3", "1/8"}, 0),
            new Question("Si lanzas dos dados, ¿cuál es la suma mínima posible?", new String[]{"1", "2", "3", "4"}, 1),
            new Question("¿Cuántos meses tienen 31 días?", new String[]{"6", "7", "8", "12"}, 1),
            new Question("¿Cuál es la probabilidad de sacar una bola azul de una bolsa con 3 azules y 2 rojas?", new String[]{"2/5", "3/5", "1/2", "1/3"}, 1),
            new Question("Si tienes 5 gallinas y cada una pone 2 huevos, ¿cuántos huevos tienes?", new String[]{"5", "7", "10", "12"}, 2)
        };
    }

    public Question[] getQuestions() {
        return questions;
    }
}