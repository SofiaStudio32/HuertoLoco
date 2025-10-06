package model;

public class QuestionsModel {
    private Question[] questions;

    public QuestionsModel() {
        questions = new Question[] {
            new Question("En una clase se registro cuantos helados prefieren los estudiantes Chocolate (8), Vainilla (12), Fresa (10). ¿cual es el sabor mas popular?", new String[]{"Chocolate", "Vainilla", "Fresa", "Ninguno"}, 1),
            new Question(" Los puntajes de un examen fueron: 5, 4, 3, 5, 4, 5. , ¿cual es la moda?", new String[]{"3", "4", "5", "6"}, 2),
            new Question("¿Cuántos lados tiene un dado tradicional?", new String[]{"4", "6", "8", "12"}, 1),
            new Question("¿Cuál es la probabilidad de sacar una moneda y que salga cara?", new String[]{"1/2", "1/3", "1/4", "1"}, 0),
            new Question(" Los datos: 1, 3, 4, 7, 9. , ¿cuál es la mediana?", new String[]{"3", "4", "7", "5"}, 1),
            new Question("¿Cuál es la probabilidad de sacar una carta roja de una baraja española?", new String[]{"1/2", "1/4", "1/3", "1/8"}, 0),
            new Question("Si lanzas dos dados, ¿cuál es la suma mínima posible?", new String[]{"1", "2", "3", "4"}, 1),
            new Question("En un curso hay 10 niñas y 15 niños. Si se elige un estudiante al azar, ¿Que probabilidad hay de escoger una niña?", new String[]{"10/25", "15/25", "5/25", "1/25"}, 0),
            new Question("¿Cuál es la probabilidad de sacar una bola azul de una bolsa con 3 azules y 2 rojas?", new String[]{"2/5", "3/5", "1/2", "1/3"}, 1),
            new Question("Si tienes 5 gallinas y cada una pone 2 huevos, ¿cuántos huevos tienes?", new String[]{"5", "7", "10", "12"}, 2)
        };
    }
    //preguntas modelos

    public Question[] getQuestions() {
        return questions;
    }
}