package controller;

import model.Farmer;
import model.Pointsmodel;
import model.Question;
import view.FarmerSelectionView;
import view.QuestionView;
import view.ResultView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

/**
 * Controlador principal del juego Huerto Loco.
 * Gestiona el flujo de selección de personaje, preguntas, poderes y resultados.
 */
public class GameController {
    // --- Modelos y datos principales ---
    private Farmer[] farmers;
    private Question[] questions;
    private Pointsmodel pointsModel;
    private int score;
    private int currentQuestion;
    private Farmer selectedFarmer;

    // --- Vistas principales ---
    private FarmerSelectionView farmerSelectionView;
    private QuestionView questionView;
    private ResultView resultView;

    // --- Timer para preguntas ---
    private Timer timer;
    private int timeLeft;

    /**
     * Constructor principal. Inicializa el juego y muestra la selección de personaje.
     */
    public GameController(Farmer[] farmers, Question[] questions, Pointsmodel pointsModel) {
        this.farmers = farmers;
        this.questions = questions;
        this.pointsModel = pointsModel;
        this.score = 0;
        this.currentQuestion = 0;
        showFarmerSelection();
    }

    /**
     * Muestra la ventana de selección de personaje y conecta el botón de tienda.
     */
    private void showFarmerSelection() {
        farmerSelectionView = new FarmerSelectionView(farmers);
        for (int i = 0; i < farmers.length; i++) {
            int idx = i;
            farmerSelectionView.addFarmerListener(idx, e -> {
                selectedFarmer = farmers[idx];
                farmerSelectionView.close();
                startGame();
            });
        }
        // Botón para abrir la tienda de poderes
        farmerSelectionView.addShopListener(e -> new ShopController(pointsModel));
    }

    /**
     * Inicia una nueva partida, reiniciando el puntaje y la pregunta actual.
     */
    private void startGame() {
        score = 0;
        currentQuestion = 0;
        showNextQuestion();
    }

    /**
     * Muestra la siguiente pregunta o los resultados si ya no hay más preguntas.
     * Configura los poderes disponibles y sus efectos.
     */
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

        // --- Configuración de poderes ---
        String[] powerNames = {"Tiempo extra", "Pista", "Doble puntaje", "Salto de pregunta"};
        int[] powerCounts = {
            pointsModel.getPower("tiempo_extra"),
            pointsModel.getPower("pista"),
            pointsModel.getPower("doble_puntaje"),
            pointsModel.getPower("salto_pregunta")
        };
        Icon[] icons = {
            emojiIcon("⏰"),
            emojiIcon("💡"),
            emojiIcon("✨"),
            emojiIcon("➡️")
        };
        questionView.setPowers(powerNames, powerCounts, icons);

        // Estados para poderes que afectan solo la pregunta actual
        final boolean[] doblePuntajeActivo = {false};
        final boolean[] pistaMostrada = {false};

        // --- Listeners para poderes ---
        // Tiempo extra: suma segundos al temporizador
        questionView.addPowerListener(0, e -> {
            if (pointsModel.usePower("tiempo_extra")) {
                timeLeft += 8;
                questionView.setTimerText("Tiempo: " + timeLeft);
                questionView.showMessage("¡Has usado Tiempo Extra! +8 segundos.");
                powerCounts[0]--;
                questionView.setPowers(powerNames, powerCounts, icons);
            }
        });

        // Pista: muestra la respuesta correcta
        questionView.addPowerListener(1, e -> {
            if (pointsModel.usePower("pista") && !pistaMostrada[0]) {
                pistaMostrada[0] = true;
                int correct = questions[currentQuestion].getCorrectIndex();
                questionView.showMessage("Pista: La respuesta correcta es \"" + questions[currentQuestion].getOptions()[correct] + "\"");
                powerCounts[1]--;
                questionView.setPowers(powerNames, powerCounts, icons);
            }
        });

        // Doble puntaje: duplica el puntaje de la pregunta actual
        questionView.addPowerListener(2, e -> {
            if (pointsModel.usePower("doble_puntaje") && !doblePuntajeActivo[0]) {
                doblePuntajeActivo[0] = true;
                questionView.showMessage("¡Doble puntaje activado para esta pregunta!");
                powerCounts[2]--;
                questionView.setPowers(powerNames, powerCounts, icons);
            }
        });

        // Salto de pregunta: pasa a la siguiente pregunta sin responder
        questionView.addPowerListener(3, e -> {
            if (pointsModel.usePower("salto_pregunta")) {
                questionView.showMessage("¡Pregunta saltada!");
                questionView.close();
                currentQuestion++;
                showNextQuestion();
                powerCounts[3]--;
            }
        });

        // --- Timer para la pregunta ---
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

        // Listener para enviar respuesta
        questionView.addSubmitListener(e -> {
            timer.stop();
            int selected = questionView.getSelectedOption();
            handleAnswer(selected);
        });
    }

    /**
     * Procesa la respuesta del usuario, aplica poderes y puntaje, y avanza a la siguiente pregunta o muestra resultados.
     */
    private void handleAnswer(int selectedIndex) {
        Question q = questions[currentQuestion];
        boolean correct = selectedIndex == q.getCorrectIndex();
        int bonus = 0;
        if (selectedIndex != -1 && correct) {
            // Bonus: 1 punto por cada 3 segundos que le sobraron
            bonus = timeLeft / 3;
        }

        // --- Lógica de puntaje y feedback visual ---
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

        // Espera 2 segundos antes de pasar a la siguiente pregunta o mostrar resultados
        new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionView.close();
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    showNextQuestion();
                } else {
                    showResults();
                }
            }
        }) {{
            setRepeats(false);
            start();
        }};
    }

    /**
     * Muestra la pantalla de resultados y guarda los puntos obtenidos.
     */
    private void showResults() {
        pointsModel.addPoints(score); // Guarda el puntaje como puntos acumulados
        resultView = new ResultView();
        resultView.setScore(score);
        resultView.addPlayAgainListener(e -> {
            resultView.close();
            showFarmerSelection();
        });
    }

    /**
     * Utilidad para crear un icono a partir de un emoji.
     */
    private Icon emojiIcon(String emoji) {
        JLabel label = new JLabel(emoji);
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        label.setSize(40, 40);
        BufferedImage image = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        label.paint(g);
        g.dispose();
        return new ImageIcon(image);
    }
}