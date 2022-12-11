package com.example.studmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lecturer {
    private final IntegerProperty lecturerName;

    public Lecturer(Integer lecturerName) {
        this.lecturerName = new SimpleIntegerProperty(lecturerName);
    }


    public IntegerProperty getLecturerCode(){
        return lecturerName;
    }
}
