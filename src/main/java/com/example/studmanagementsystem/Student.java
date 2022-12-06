package com.example.studmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final IntegerProperty studCode;
    private final StringProperty studName;
    private final IntegerProperty courseCode;
    private final IntegerProperty campusCode;
    private final IntegerProperty academicYear;
    private final StringProperty studEmail;
    private final IntegerProperty userCode;

    public Student(Integer studCode, String studName, Integer courseCode, Integer campusCode, Integer academicYear, String studEmail, Integer userCode) {
        this.studCode = new SimpleIntegerProperty(studCode);
        this.studName = new SimpleStringProperty(studName);
        this.courseCode = new SimpleIntegerProperty(courseCode);
        this.campusCode = new SimpleIntegerProperty(campusCode);
        this.academicYear = new SimpleIntegerProperty(academicYear);
        this.studEmail = new SimpleStringProperty(studEmail);
        this.userCode = new SimpleIntegerProperty(userCode);
    }
    public IntegerProperty getstudCode(){return studCode;}
    public StringProperty getstudName(){return studName;}
    public IntegerProperty getcourseCode(){return courseCode;}
    public IntegerProperty getcampusCode(){return campusCode;}
    public IntegerProperty getacademicYear(){return academicYear;}
    public StringProperty getstudEmail(){return studEmail;}
    public IntegerProperty getuserCode(){return userCode;}


}

