package model;

import java.awt.Color;
import model.SpecialPowerType;

public class Farmer {
    private String name;
    private String description;
    private Color color; // Color representativo del personaje

    // --- Poder especial ---
    private SpecialPowerType specialPowerType;
    private int specialPowerValue;
    private String specialPowerDescription;

    public Farmer(String name, String description, Color color,
                  SpecialPowerType specialPowerType, int specialPowerValue, String specialPowerDescription) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.specialPowerType = specialPowerType;
        this.specialPowerValue = specialPowerValue;
        this.specialPowerDescription = specialPowerDescription;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Color getColor() { return color; }

    // --- Getters para el poder especial ---
    public SpecialPowerType getSpecialPowerType() { return specialPowerType; }
    public int getSpecialPowerValue() { return specialPowerValue; }
    public String getSpecialPowerDescription() { return specialPowerDescription; }
}
