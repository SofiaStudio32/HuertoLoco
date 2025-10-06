package model;

import java.io.*;
import java.util.*;

/**
 * Modelo para gestionar los puntos y poderes del usuario.
 * Guarda y carga los datos en un archivo de texto en la carpeta del usuario.
 */
public class Pointsmodel {
    // --- Puntos acumulados ---
    private int points;
    // --- Cantidad de poderes por tipo ---
    private Map<String, Integer> powers;
    // --- Archivo físico donde se guardan los datos ---
    private final File pointsFile;
    // --- Nombres de los poderes disponibles ---
    private final String[] powerNames = {"tiempo_extra", "pista", "doble_puntaje", "salto_pregunta"};

    /**
     * Constructor. Inicializa el modelo y carga los datos desde el archivo.
     */
    public Pointsmodel() {
        String userHome = System.getProperty("user.home");
        pointsFile = new File(userHome, "huertoloco_points.txt");
        powers = new HashMap<>();
        loadData();
    }

    /**
     * Carga los puntos y poderes desde el archivo físico.
     * Si el archivo no existe, inicializa los valores en cero.
     */
    private void loadData() {
        points = 0;
        for (String p : powerNames) powers.put(p, 0);
        if (!pointsFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(pointsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("puntos=")) points = Integer.parseInt(line.split("=")[1].trim());
                for (String p : powerNames) {
                    if (line.startsWith(p + "=")) powers.put(p, Integer.parseInt(line.split("=")[1].trim()));
                }
            }
        } catch (Exception e) {
            // Si hay error de lectura, se ignora y se usan valores por defecto
        }
    }

    /**
     * Devuelve los puntos acumulados.
     */
    public int getPoints() { return points; }

    /**
     * Devuelve la cantidad disponible de un poder específico.
     * @param name Nombre del poder (ej: "tiempo_extra")
     */
    public int getPower(String name) { return powers.getOrDefault(name, 0); }

    /**
     * Suma puntos al total y guarda el cambio en el archivo.
     * @param amount Cantidad de puntos a sumar.
     */
    public void addPoints(int amount) {
        points += amount;
        saveData();
    }

    /**
     * Resta puntos si hay suficientes y guarda el cambio en el archivo.
     * @param amount Cantidad de puntos a gastar.
     * @return true si se pudo gastar, false si no había suficientes puntos.
     */
    public boolean spendPoints(int amount) {
        if (points >= amount) {
            points -= amount;
            saveData();
            return true;
        }
        return false;
    }

    /**
     * Suma uno a la cantidad de un poder específico y guarda el cambio.
     * @param name Nombre del poder.
     */
    public void addPower(String name) {
        powers.put(name, powers.getOrDefault(name, 0) + 1);
        saveData();
    }

    /**
     * Usa (resta uno) un poder si hay disponibilidad y guarda el cambio.
     * @param name Nombre del poder.
     * @return true si se pudo usar, false si no había disponibilidad.
     */
    public boolean usePower(String name) {
        int current = powers.getOrDefault(name, 0);
        if (current > 0) {
            powers.put(name, current - 1);
            saveData();
            return true;
        }
        return false;
    }

    /**
     * Guarda los puntos y poderes en el archivo físico.
     */
    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pointsFile))) {
            writer.write("puntos=" + points + "\n");
            for (String p : powerNames) {
                writer.write(p + "=" + powers.getOrDefault(p, 0) + "\n");
            }
        } catch (IOException e) {
            // Si hay error de escritura, se ignora
        }
    }
}