package controller;

import model.Farmer;
import model.Question;
import view.FarmerSelectionView;
import view.QuestionView;
import view.ResultView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private Farmer[] farmers;
    private Question[] questions;
    private int score;
    private int currentQuestion;
    private Farmer selectedFarmer;

    private FarmerSelectionView farmerSelectionView;
    private QuestionView questionView;
    private ResultView resultView;

    private Timer timer;
    private int timeLeft;

    public GameController(Farmer[] farmers, Question[] questions) {
        this.farmers = farmers;
        this.questions = questions;
        this.score = 0;
        this.currentQuestion = 0;
        showFarmerSelection();
    }

    private void showFarmerSelection() {
        farmerSelectionView = new FarmerSelectionView(farmers);
        for (int i = 0; i < farmers.length; i++) {
            int idx = i;
            farmerSelectionView.addFarmerListener(i, e -> {
                selectedFarmer = farmers[idx];
                farmerSelectionView.close();
                startGame();
            });
        }
    }

    private void startGame() {
        score = 0;
        currentQuestion = 0;
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentQuestion >= questions.length) {
            showResults();
            return;
        }
        Question q = questions[currentQuestion];
        questionView = new QuestionView(q.getOptions().length);
        questionView.setQuestion(q.getQuestion(), q.getOptions());
        timeLeft = 12;
        questionView.setTimerText("Tiempo: " + timeLeft);

        timer = new Timer(1000, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                questionView.setTimerText("Tiempo: " + timeLeft);
                if (timeLeft <= 0) {
                    timer.stop();
                    handleAnswer(-1); // No respondió a tiempo
                }
            }
        });
        timer.start();

        questionView.addSubmitListener(e -> {
            timer.stop();
            int selected = questionView.getSelectedOption();
            handleAnswer(selected);
        });
    }

    private void handleAnswer(int selectedIndex) {
        Question q = questions[currentQuestion];
        boolean correct = selectedIndex == q.getCorrectIndex();
        int bonus = 0;
        if (selectedIndex != -1 && correct) {
            // Bonus: 1 punto por cada 3 segundos que le sobraron
            bonus = timeLeft / 3;
        }
        if (selectedIndex == -1) {
            questionView.setFeedback("¡Tiempo agotado! La respuesta correcta era: " + q.getOptions()[q.getCorrectIndex()], false, q.getCorrectIndex(), -1);
            questionView.setAvatarMood(false); // triste
            score -= 1;
        } else if (correct) {
            questionView.setFeedback("¡Correcto! ¡Bien hecho, " + selectedFarmer.getName() + "! Bonus: +" + bonus, true, q.getCorrectIndex(), selectedIndex);
            questionView.setAvatarMood(true); // feliz
            score += 2 + bonus;
        } else {
            questionView.setFeedback("Incorrecto. ¡Ánimo, " + selectedFarmer.getName() + "!", false, q.getCorrectIndex(), selectedIndex);
            questionView.setAvatarMood(false); // triste
            score -= 1;
        }

        // Espera 2 segundos antes de pasar a la siguiente pregunta
        new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionView.close();
                currentQuestion++;
                showNextQuestion();
            }
        }) {{
            setRepeats(false);
            start();
        }};
    }

    private void showResults() {
        resultView = new ResultView();
        resultView.setScore(score);
        resultView.addPlayAgainListener(e -> {
            resultView.close();
            showFarmerSelection();
        });
    }
}