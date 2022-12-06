package com.example.studmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Campus {
    private final StringProperty campusCode;
    private final StringProperty county;

    public Campus(String campusCode, String county) {
        this.campusCode = new SimpleStringProperty(campusCode);
        this.county = new SimpleStringProperty(county);
    }
    public StringProperty getcampusCode(){return campusCode;}
    public StringProperty getcounty(){return county;}
}
