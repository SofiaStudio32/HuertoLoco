package model;

public class MainModel {
    // This class represents the data and business logic of the "Huerto Loco" application.
    
    // Example fields to represent data
    private String plantName;
    private int plantCount;

    // Constructor
    public MainModel() {
        this.plantName = "";
        this.plantCount = 0;
    }

    // Method to set plant details
    public void setPlantDetails(String name, int count) {
        this.plantName = name;
        this.plantCount = count;
    }

    // Method to get plant name
    public String getPlantName() {
        return plantName;
    }

    // Method to get plant count
    public int getPlantCount() {
        return plantCount;
    }

    // Additional methods to manipulate and retrieve data can be added here
}