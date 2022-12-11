package com.example.studmanagementsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private final StringProperty courseName;
    private final StringProperty lecturerCode;
    private final StringProperty venueCode;

    public Course(String courseName, String lecturerCode, String venueCode) {
        this.courseName = new SimpleStringProperty(courseName);
        this.lecturerCode = new SimpleStringProperty(lecturerCode);
        this.venueCode = new SimpleStringProperty(venueCode);
    }

    public StringProperty getCourseName(){return courseName;}
    public StringProperty getLecturerCode(){return lecturerCode;}
    public StringProperty getVenueCode(){return venueCode;}
}

