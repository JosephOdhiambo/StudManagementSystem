package com.example.studmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CourseController implements Initializable {
    private DataAccessObject dao;
    @FXML
    private JFXButton btn_lectName;

    @FXML
    private JFXButton btn_venueName;

    @FXML
    private Label course_title;
    @FXML
    private JFXButton btn_saveCourse;
    @FXML
    private TableColumn<Course, String> column_Lecture;

    @FXML
    private TableColumn<Course, String> column_courseName;

    @FXML
    private TableColumn<Course, String> column_venue;

    @FXML
    private TextField txt_courseName;
    @FXML
    private TableView<Course> tbl_courses;
    private String query;

    @FXML
    private JFXComboBox<String> combo_lecturer;

    @FXML
    private JFXComboBox<String> combo_venue;
    private String lecturer, venue;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dao = new DataAccessObject();
        btn_saveCourse.setOnAction(e->{
            insertNewCourse();
        });
        initVenue();
        initLecturer();
        refreshTable();
    }

    private void initVenue() {
        combo_venue.getSelectionModel().clearSelection();
        query = "SELECT class_name FROM class";
        combo_venue.setItems(dao.getLecturer(query));
    }

    private void initLecturer() {
        combo_lecturer.getSelectionModel().clearSelection();
        query = "SELECT lecturer_name FROM lecturer";
        combo_lecturer.setItems(dao.getLecturer(query));
    }

    private void insertNewCourse() {
        Course selected = tbl_courses.getSelectionModel().getSelectedItem();
        lecturer = combo_lecturer.getSelectionModel().getSelectedItem();
        venue = combo_venue.getSelectionModel().getSelectedItem();
        query = "INSERT INTO course VALUES(null, '" +txt_courseName.getText()+ "', (SELECT lecturer_code FROM lecturer WHERE lecturer_name='"+lecturer+"'), (SELECT venue_code FROM class WHERE class_name='"+venue+"'))";
        dao.saveData(query);
        txt_courseName.setText("");
        refreshTable();

    }
    private void initTable() {
        column_courseName.setCellValueFactory(cell->cell.getValue().getCourseName());
        column_Lecture.setCellValueFactory(cell->cell.getValue().getLecturerCode());
        column_venue.setCellValueFactory(cell->cell.getValue().getVenueCode());
    }
    private void refreshTable() {
        initTable();
        query = "SELECT course_name, lecturer_code, venue_code FROM course";
        tbl_courses.setItems(dao.getCoursesData(query));
    }


}
