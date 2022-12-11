package com.example.studmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Venue {
    private final IntegerProperty venueName;

    public Venue(Integer venueName) {
        this.venueName = new SimpleIntegerProperty(venueName);
    }
}
