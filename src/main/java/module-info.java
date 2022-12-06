module com.example.studmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.example.studmanagementsystem to javafx.fxml;
    exports com.example.studmanagementsystem;
}