package com.example.studmanagementsystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataAccessObject {
    private DBConnection database = new DBConnection();
    private ResultSet rs;
    private PreparedStatement pstmt;
    private Connection connect;


    public DataAccessObject(){

    }
    public void saveData(String query){
        try{
            connect = database.getConnection(); //get connection
            pstmt = connect.prepareStatement(query);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            database.close(connect, pstmt, null);
        }
    }
    public ObservableList<User> getUserData(String query){
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(new User(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public ObservableList<Campus> getCampusData(String query){
        ObservableList<Campus> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(new Campus(rs.getString(1), rs.getString(2)));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    public ObservableList<Course> getCoursesData(String query){
        ObservableList<Course> list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public ObservableList<String> getLecturer(String query){
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(rs.getString(1));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<Integer> getVenue(String query){
        ObservableList list = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                list.add(rs.getInt(1));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<String> getCourseComboBox(String query){
        ObservableList ls = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ls.add(rs.getString(1));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return ls;
    }

    public ObservableList<String> getCourseCombo(String query){
        ObservableList ls = FXCollections.observableArrayList();
        try {
            connect = database.getConnection();
            pstmt = connect.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ls.add(rs.getString(1));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return ls;
    }

}
