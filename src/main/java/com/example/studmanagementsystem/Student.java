package com.example.studmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty studCode;
    private final StringProperty studName;
    private final StringProperty courseCode;
    private final StringProperty campusCode;
    private final StringProperty academicYear;
    private final StringProperty studEmail;
    private final StringProperty userCode;

    public Student(String studCode, String studName, String courseCode, String campusCode, String academicYear, String studEmail, String userCode) {
        this.studCode = new SimpleStringProperty(studCode);
        this.studName = new SimpleStringProperty(studName);
        this.courseCode = new SimpleStringProperty(courseCode);
        this.campusCode = new SimpleStringProperty(campusCode);
        this.academicYear = new SimpleStringProperty(academicYear);
        this.studEmail = new SimpleStringProperty(studEmail);
        this.userCode = new SimpleStringProperty(userCode);
    }

    public StringProperty getStudCode() {return studCode;}
    public StringProperty getstudName(){return studName;}
    public StringProperty getcourseCode(){return courseCode;}
    public StringProperty getcampusCode(){return campusCode;}
    public StringProperty getacademicYear(){return academicYear;}
    public StringProperty getstudEmail(){return studEmail;}
    public StringProperty getuserCode(){return userCode;}


}

