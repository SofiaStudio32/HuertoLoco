package model;

import model.SpecialPowerType;

public class FarmersModel {
    private Farmer[] farmers;

    public FarmersModel() {
        farmers = new Farmer[] {
            // Juan: Puntaje extra por respuesta correcta, pero tiempo normal
            new Farmer("Juan", "El granjero alegre", new java.awt.Color(255, 204, 102),
                SpecialPowerType.BONUS_SCORE, 1, "Gana +1 punto extra por respuesta correcta"),

            // Ana: Menos tiempo para responder, pero puede usar una pista extra
            new Farmer("Ana", "La experta en semillas", new java.awt.Color(153, 204, 255),
                SpecialPowerType.EXTRA_HINT, 1, "Puede ver una pista extra en cada partida"),

            // Luis: Más tiempo para responder, pero no gana bonus de puntaje
            new Farmer("Luis", "El cuidador de animales", new java.awt.Color(204, 255, 153),
                SpecialPowerType.LESS_TIME, -3, "Tiene +3 segundos para responder cada pregunta"),

            // Sofía: Puede saltar una pregunta por partida, pero no tiene bonus de tiempo ni puntaje
            new Farmer("Sofía", "La ingeniera agrícola", new java.awt.Color(255, 153, 204),
                SpecialPowerType.EXTRA_HINT, 1, "Puede saltar una pregunta por partida")
        };
    }

    public Farmer[] getFarmers() {
        return farmers;
    }
}