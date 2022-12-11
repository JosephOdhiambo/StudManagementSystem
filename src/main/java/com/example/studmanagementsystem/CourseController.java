package com.example.studmanagementsystem;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    private JFXButton btn_lectCode;

    @FXML
    private JFXButton btn_venueCode;

    @FXML
    private Label course_title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        course_title.setText("Hi there!");
        btn_lectCode.setOnAction(e->{

        });
    }
}
