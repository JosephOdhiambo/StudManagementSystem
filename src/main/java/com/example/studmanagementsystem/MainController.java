package com.example.studmanagementsystem;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    private FXMLLoader loader;
    @FXML
    private AnchorPane studentPane;

    @FXML
    private JFXButton studentsBTN;

    @FXML
    private AnchorPane unitPane;
    @FXML
    private TableView<Student> tbl_Student;

    @FXML
    private JFXButton btn_refreshCampus;

    @FXML
    private JFXButton btn_refreshCourse;

    @FXML
    private JFXButton campusBTN;

    @FXML
    private JFXButton unitsBTN;
    @FXML
    private TextField txt_county;

    @FXML
    private JFXButton userBTN;

    @FXML
    private AnchorPane userPane;

    @FXML
    private TableColumn<Campus, String> column_campusCode;

    @FXML
    private TableColumn<Campus, String> column_campusCounty;

    @FXML
    private JFXButton newSTudentBTN;

    @FXML
    private TableColumn<User, String> column_code;
    @FXML
    private JFXComboBox<Integer> combo_usertype;
    @FXML
    private AnchorPane campusPane;

    @FXML
    private TableColumn<User, String> column_password;


    @FXML
    private TableColumn<User, String> column_username;
    @FXML
    private JFXButton submitCampusBTN;

    @FXML
    private TableView<Campus> tbl_campus;
    @FXML
    private JFXButton classesBTN;
    @FXML
    private AnchorPane classesPane;

    @FXML
    private TableColumn<User, String> column_usertype;

    @FXML
    private JFXButton new_BTN;
    @FXML
    private TableView<User> tbl_view;
    private String query;
    private DataAccessObject dao;

    @FXML
    private JFXButton delete_BTN;

    @FXML
    private JFXButton edit_BTN;
    @FXML
    private JFXButton submit_BTN;
    private String usercode, username, usertype, password, county;

    private boolean EDIT=false, ADD=true;



    @FXML
    private PasswordField txt_password;
    private PasswordField passwordField;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_usertype;


    @FXML
    private JFXButton btn_refreshUserName;
    private int ID;
    @FXML
    private ImageView drawerImage;
    @FXML
    private AnchorPane opacityPane;

    private boolean Transitioned = false;
    @FXML
    private TextField txt_studname;
    @FXML
    private JFXComboBox<String> combo_course;
    @FXML
    private JFXComboBox<String> combo_campus;
    @FXML
    private TextField txt_academicYear;
    @FXML
    private TextField txt_email;
    @FXML
    private JFXComboBox<String> combo_userName;
    @FXML
    private JFXButton btn_course;
    @FXML
    private JFXButton btn_campus;
    @FXML
    private JFXButton btn_username;
    @FXML
    private JFXButton student_Submit;
    private String code, name, course, campus, year, email, unamestud;


    @FXML
    private TableColumn<Student, String> column_studCode;
    @FXML
    private TableColumn<Student, String> column_studName;
    @FXML
    private TableColumn<Student, String> column_studCourseCode;

    @FXML
    private TableColumn<Student, String> column_studCampusCode;
    @FXML
    private TableColumn<Student, String> column_studAcademicYear;
    @FXML
    private TableColumn<Student, String> column_studentEmail;
    @FXML
    private TableColumn<Student, String> column_userName;

    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordField = new PasswordField();
        dao = new DataAccessObject();
        initGender();
        drawerImage.setOnMouseClicked(e->{
            drawerImage.setDisable(true);
            if(Transitioned==false) {
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), opacityPane);
                translateTransition1.setByX(-600);
                translateTransition1.play();
                Transitioned=true;
                translateTransition1.setOnFinished(event->{
                    drawerImage.setDisable(false);
                });
            }
            else if(Transitioned){
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), opacityPane);
                translateTransition1.setByX(600);
                translateTransition1.play();
                Transitioned = false;
                translateTransition1.setOnFinished(event->{
                    drawerImage.setDisable(false);
                });
            }



        });
        userPane.setVisible(true);
        userBTN.setOnAction(e->{
            refreshTable();
            if(studentPane.isVisible() || unitPane.isVisible() || classesPane.isVisible() || campusPane.isVisible()){
                studentPane.setVisible(false);
                unitPane.setVisible(false);
                classesPane.setVisible(false);
                campusPane.setVisible(false);
            }
            userPane.setVisible(true);
        });
        unitsBTN.setOnAction(e->{
            if(userPane.isVisible() || studentPane.isVisible() || classesPane.isVisible() || campusPane.isVisible()){
                userPane.setVisible(false);
                studentPane.setVisible(false);
                classesPane.setVisible(false);
                campusPane.setVisible(false);
            }
            unitPane.setVisible(true);
        });
        studentsBTN.setOnAction(e->{
            initCourseCombo();
            initUsernameCombo();
            initCampusCombo();
            insertNewStudent();
            if(unitPane.isVisible() || userPane.isVisible() || classesPane.isVisible() || campusPane.isVisible()){
                unitPane.setVisible(false);
                userPane.setVisible(false);
                classesPane.setVisible(false);
                campusPane.setVisible(false);
            }
            studentPane.setVisible(true);

        });
        classesBTN.setOnAction(e->{
            if(unitPane.isVisible() || studentPane.isVisible() || userPane.isVisible() || campusPane.isVisible()){
                unitPane.setVisible(false);
                userPane.setVisible(false);
                studentPane.setVisible(false);
                campusPane.setVisible(false);
            }
            classesPane.setVisible(true);
        });

        campusBTN.setOnAction(e->{
            refreshCampusTable();
            if(unitPane.isVisible() || studentPane.isVisible() || userPane.isVisible() || classesPane.isVisible()){
                unitPane.setVisible(false);
                userPane.setVisible(false);
                studentPane.setVisible(false);
                classesPane.setVisible(false);
            }
            campusPane.setVisible(true);
        });
        btn_course.setOnMouseClicked(e->{
            showCourse();
        });

        submit_BTN.setOnAction(e->{
            saveAccount();
            insertNewUser();
        });
        student_Submit.setOnAction(e->{
            saveStudent();
        });

        submitCampusBTN.setOnAction(e->{
            saveCampus();
        });


        edit_BTN.setOnAction(e->{
            EDIT = true;
            ADD = false;
            editTable();
        });

        newSTudentBTN.setOnAction(e->{
            insertNewStudent();
        });
        tbl_Student.setOnMouseClicked(e->{
            EDIT = true;
            ADD = false;
            editStudents();
        });
        tbl_view.setOnMouseClicked(e->{
            EDIT = true;
            ADD = false;
            editTable();
        });
        btn_refreshCourse.setOnAction(e->{
            initCourseCombo();
        });
        btn_refreshUserName.setOnAction(e->{
            initUsernameCombo();
        });

        btn_refreshCampus.setOnMouseClicked(e->{
            initCampusCombo();
        });


        new_BTN.setOnAction(e->{
            insertNewUser();
        });


        delete_BTN.setOnAction(e->{
            deleteUser();
        });
        initUsernameCombo();
        initCampusCombo();
        initTable();
        initCourseCombo();
        refreshTable();
        refreshStudTable();
        refreshCampusTable();
    }

    private void insertNewStudent() {
        Student selected  = tbl_Student.getSelectionModel().getSelectedItem();
        EDIT = false;
        ADD = true;
        txt_studname.setText("");
        combo_course.getSelectionModel().clearSelection();
        combo_campus.getSelectionModel().clearSelection();
        txt_academicYear.setText("");
        txt_email.setText("");
        combo_userName.getSelectionModel().clearSelection();
    }

    private void editStudents() {
        try{
            Student selected = tbl_Student.getSelectionModel().getSelectedItem();
            txt_studname.setText(selected.getstudName().get());
            combo_course.getSelectionModel().select(selected.getcourseCode().get());
            combo_campus.getSelectionModel().select(selected.getcampusCode().get());
            txt_academicYear.setText(selected.getacademicYear().get());
            txt_email.setText(selected.getstudEmail().get());
            combo_userName.getSelectionModel().select(selected.getuserCode().get());
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    private void saveStudent() {
        name = txt_studname.getText();
        course = combo_course.getSelectionModel().getSelectedItem();
        campus = combo_campus.getSelectionModel().getSelectedItem();
        year = txt_academicYear.getText();
        email = txt_email.getText();
        unamestud = combo_userName.getSelectionModel().getSelectedItem();
        if(EDIT){
            Student selected = tbl_Student.getSelectionModel().getSelectedItem();
            code = selected.getStudCode().get();
            query = "UPDATE `student` SET `studname`='"+name+"',`course_code`=(SELECT course_code FROM course WHERE course_name='"+course+"'),`campus_code`=(SELECT campus_code from campus WHERE county='"+campus+"'),`academic_year`='"+year+"',`stud_email`='"+email+"',`user_code`=(SELECT user_code FROM users WHERE username='"+unamestud+"') WHERE student_code='"+code+"'";
        }
        else if(ADD) {
            query = "INSERT INTO student VALUES(null, '" + name + "', (SELECT course_code FROM course WHERE course_name='" + course + "'), (SELECT campus_code FROM campus WHERE county='" + campus + "'),'" + year + "','" + email + "',(SELECT user_code FROM users WHERE username='" + unamestud + "'))";
        }
        dao.saveData(query);
        txt_studname.setText("");
        combo_course.getSelectionModel().select(0);
        combo_campus.getSelectionModel().select(0);
        txt_academicYear.setText("");
        txt_email.setText("");
        combo_userName.getSelectionModel().select(0);
        refreshStudTable();
    }

    private void refreshStudTable() {
        initStudents();
        insertNewStudent();
        query = "select student_code, studname, course.course_name, campus.county, academic_year, stud_email, users.username from student\n" +
                "JOIN course ON student.course_code = course.course_code\n" +
                "JOIN campus ON student.campus_code = campus.campus_code\n" +
                "JOIN users ON student.user_code = users.user_code";
        tbl_Student.setItems(dao.getStudentData(query));

    }

    private void initStudents() {
        column_studCode.setCellValueFactory(cell->cell.getValue().getStudCode());
        column_studName.setCellValueFactory(cell->cell.getValue().getstudName());
        column_studCourseCode.setCellValueFactory(cell->cell.getValue().getcourseCode());
        column_studCampusCode.setCellValueFactory(cell->cell.getValue().getcampusCode());
        column_studAcademicYear.setCellValueFactory(cell->cell.getValue().getacademicYear());
        column_studentEmail.setCellValueFactory(cell->cell.getValue().getstudEmail());
        column_userName.setCellValueFactory(cell->cell.getValue().getuserCode());
    }


    private void initUsernameCombo() {
        combo_userName.getSelectionModel().clearSelection();
        query = "SELECT username from users";
        combo_userName.setItems(dao.getUserNameCombo(query));
    }

    private void initCampusCombo() {
        combo_campus.getSelectionModel().clearSelection();
        query = "SELECT county FROM campus";
        combo_campus.setItems(dao.getCourseCombo(query));
    }

    private void initCourseCombo() {
        combo_course.getSelectionModel().clearSelection();
        query = "SELECT course_name FROM course";
        combo_course.setItems(dao.getCourseComboBox(query));
    }




    private void showCourse() {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Course.fxml"));
            CourseController controller = new CourseController();
            loader.setController(controller);
            loader.load();
            Scene scene = new Scene(loader.getRoot());
            scene.getStylesheets().add(getClass().getResource("Course.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCampus() {
        county = txt_county.getText();
        query = "INSERT INTO campus VALUES(null, '"+county+"')";
        dao.saveData(query);
        txt_county.setText("");
        refreshCampusTable();
    }

    private void refreshCampusTable() {
        initCampus();
        query = "select campus_code , county from campus";
        tbl_campus.setItems(dao.getCampusData(query));
    }

    private void initCampus() {
        column_campusCode.setCellValueFactory(cell -> cell.getValue().getcampusCode());
        column_campusCounty.setCellValueFactory(cell-> cell.getValue().getcounty());
    }

    private void initGender() {
        List<Integer> list = new ArrayList<Integer>();

        // foreach loop
        list.add(0);
        list.add(1);
        // convert list to observable list
        ObservableList obList = FXCollections.observableArrayList(list);
        combo_usertype.setItems(obList);
        combo_usertype.getSelectionModel().select(1);
    }

    private void insertNewUser() {
        User selected = tbl_view.getSelectionModel().getSelectedItem();
        EDIT = false;
        ADD = true;
        txt_username.setText("");
        combo_usertype.getSelectionModel().select(1);
        txt_password.setText("");
    }

    private void editTable() {
        User selected = tbl_view.getSelectionModel().getSelectedItem();
        ID = Integer.parseInt(selected.getUserCode().get());
        txt_username.setText(selected.getUserName().get());
        combo_usertype.getSelectionModel().select(Integer.parseInt(selected.getUserType().get()));
        txt_password.setText(selected.getPassword().get());
    }

    private void saveAccount() {
        username = txt_username.getText();
        usertype = combo_usertype.getSelectionModel().getSelectedItem().toString();
        password = txt_password.getText();
        if(EDIT){
            query = "UPDATE users SET username='"+username+"', u_type='"+usertype+"', password='"+password+"' WHERE user_code ="+ID;
        }
        else if(ADD){
            query = "INSERT INTO users VALUES(null, '" + username + "','" + usertype + "','" + password + "')";
        }


        dao.saveData(query);

        txt_username.setText("");
        combo_usertype.getSelectionModel().select(1);
        txt_password.setText("");

        refreshTable();
    }
    private void initTable() {
        column_code.setCellValueFactory(cell -> cell.getValue().getUserCode());
        column_username.setCellValueFactory(cell -> cell.getValue().getUserName());
        column_usertype.setCellValueFactory(cell -> cell.getValue().getUserType());
        column_password.setCellValueFactory(cell -> cell.getValue().getPassword());
    }
    private void deleteUser(){
        User selected = tbl_view.getSelectionModel().getSelectedItem();
        ID = Integer.parseInt(selected.getUserCode().get());
        query = "DELETE FROM users WHERE user_code ='"+ID+"';";
        dao.saveData(query);
        refreshTable();
    }

    private void refreshTable() {
        initTable();
        query = "select user_code, username, u_type, password from users";
        tbl_view.setItems(dao.getUserData(query));
    }


}