package com.example.studmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final StringProperty UserCode;
    private final StringProperty UserName;
    private final StringProperty UserType;
    private final StringProperty Password;

    public User(String UserCode, String UserName, String UserType, String Password){
        this.UserCode = new SimpleStringProperty(UserCode);
        this.UserName = new SimpleStringProperty(UserName);
        this.UserType = new SimpleStringProperty(UserType);
        this.Password = new SimpleStringProperty(Password);
    }
    public StringProperty getUserCode(){return UserCode;}
    public StringProperty getUserName(){return UserName;}
    public StringProperty getUserType(){
        return UserType;
    }
    public StringProperty getPassword(){
        return Password;
    }


}
