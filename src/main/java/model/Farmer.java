package model;

import java.awt.Color;

public class Farmer {
    private String name;
    private String description;
    private Color color; // Color representativo del personaje

    public Farmer(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public Color getColor() { return color; }
}
