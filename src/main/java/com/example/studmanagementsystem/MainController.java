package com.example.studmanagementsystem;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    private AnchorPane studentPane;

    @FXML
    private JFXButton studentsBTN;

    @FXML
    private AnchorPane unitPane;

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
    private TableColumn<User, String> column_code;
    @FXML
    private JFXComboBox<?> combo_usertype;
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
    private TextField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_usertype;
    private int ID;
    @FXML
    private ImageView drawerImage;
    @FXML
    private AnchorPane opacityPane;

    private boolean Transitioned = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            refreshStudTable();
            if(unitPane.isVisible() || studentPane.isVisible() || userPane.isVisible() || classesPane.isVisible()){
                unitPane.setVisible(false);
                userPane.setVisible(false);
                studentPane.setVisible(false);
                classesPane.setVisible(false);
            }
            campusPane.setVisible(true);
        });

        submit_BTN.setOnAction(e->{
            saveAccount();
            insertNewUser();
        });

        submitCampusBTN.setOnAction(e->{
            saveCampus();
        });

        edit_BTN.setOnAction(e->{
            EDIT = true;
            ADD = false;
            editTable();
        });
        new_BTN.setOnAction(e->{
            insertNewUser();
        });

        delete_BTN.setOnAction(e->{
            deleteUser();
        });



        initTable();
        refreshTable();
        refreshStudTable();
    }

    private void saveCampus() {
        county = txt_county.getText();
        query = "INSERT INTO campus VALUES(null, '"+county+"')";
        dao.saveData(query);
        txt_county.setText("");
        refreshStudTable();
    }

    private void refreshStudTable() {
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