package com.example.javachatroom;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCstuffs {

    private PreparedStatement pstmt;
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Jo73n5634434#";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/newsSystem?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf-8";


    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button cancelbut;
    private ResultSet resultSet;

    @FXML
    private void smogabutton() throws Exception {
        String username1=username.getText();
        String password1=password.getText();
        System.out.println(username1+" dengan password "+password1);
    }


    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) cancelbut.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}