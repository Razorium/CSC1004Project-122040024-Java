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
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ok1?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf-8";


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

        try(Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);) {

            String sql = "select * from `usnpass` where usn = ? and pass = ?";
            List<Object> params = new ArrayList<>();
            params.add(username1);
            params.add(password1);

            pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, params.get(0));
            pstmt.setObject(2, params.get(1));
            resultSet = pstmt.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int col = metaData.getColumnCount();
            int counter=0;
            while (resultSet.next()) {
                counter++;
            }
            if (counter==0){
                System.out.println("Either password or username is incorrect.");
            }else{
                System.out.println("It worked!!!!!");

            }


            System.out.println("Connected database successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}