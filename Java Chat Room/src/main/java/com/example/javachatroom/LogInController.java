package com.example.javachatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.*;

public class LogInController{
    public static void createnewaccount(ActionEvent event, String username, String password, String age, String address, String gender){
        Connection connection = null;
        PreparedStatement pstmt = null;
        PreparedStatement existsornot = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ok1?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf-8", "root", "Jo73n5634434#");
            existsornot = connection.prepareStatement("SELECT * FROM usnpass WHERE usn = ?");
            existsornot.setString(1, username);
            resultSet = existsornot.executeQuery();

            if(resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("An account with this username has already existed");
                alert.show();
            } else {
                pstmt = connection.prepareStatement("INSERT INTO usnpass (usn, pass, age, address, gender) VALUES (?, ?, ?, ?, ?)");
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, age);
                pstmt.setString(4, address);
                pstmt.setString(5, gender);
                pstmt.executeUpdate();

                sceneChanging.changescene(event, "login-view.fxml", "Login", username, password);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(existsornot!=null){
                try{
                    existsornot.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(pstmt!=null){
                try{
                    pstmt.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!= null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    public static void LoginUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ok1?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf-8", "root", "Jo73n5634434#");
            preparedStatement = connection.prepareStatement("SELECT pass FROM usnpass WHERE usn = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The username or password you entered might be incorrect");
                alert.show();
            } else {
                while(resultSet.next()){
                    String getPassword = resultSet.getString("pass");
                    if(getPassword.equals(password)){
                        sceneChanging.changescenetomain(event, "userview.fxml", "Java Chat Room", username);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The username or password you entered might be incorrect");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try{
                    preparedStatement.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}