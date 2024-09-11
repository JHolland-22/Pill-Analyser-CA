package com.example.worksheet1;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class allPillPixels {

    private String pillName;
    private  String dosage;
    private String description;


    public allPillPixels(String pillName, String dosage, String description) {
        this.pillName = pillName;
        this.dosage = dosage;
        this.description = description;
    }

    @Override
    public String toString() {
        return "allPillPixels{" +
                "pillName='" + pillName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", description=" + description +
                '}';
    }


    public String getPillName() {
        return pillName;
    }

    public void setPillName(String pillName) {
        this.pillName = pillName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int add(allPillPixels pill) {
        return 0;
    }

    public void displayHashTable() {

    }
}
