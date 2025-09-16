package model;

public class FarmersModel {
    private Farmer[] farmers;

    public FarmersModel() {
        farmers = new Farmer[] {
            new Farmer("Juan", "El granjero alegre", new java.awt.Color(255, 204, 102)),
            new Farmer("Ana", "La experta en semillas", new java.awt.Color(153, 204, 255)),
            new Farmer("Luis", "El cuidador de animales", new java.awt.Color(204, 255, 153)),
            new Farmer("Sofía", "La ingeniera agrícola", new java.awt.Color(255, 153, 204))
        };
    }

    public Farmer[] getFarmers() {
        return farmers;
    }
}