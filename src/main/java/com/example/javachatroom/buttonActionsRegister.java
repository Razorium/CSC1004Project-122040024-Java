package com.example.javachatroom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class buttonActionsRegister implements Initializable{
    @FXML
    private Button createnewbutton;
    @FXML
    private TextField usernameregister;
    @FXML
    private TextField passwordregister;
    @FXML
    private TextField age;
    @FXML
    private TextField gender;
    @FXML
    private TextField address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        createnewbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String input1 = usernameregister.getText();
                String input2 = passwordregister.getText();
                String input3 = age.getText();
                String input4 = address.getText();
                String input5 = gender.getText();
                LogInController.createnewaccount(event, input1, input2, input3, input4, input5);
            }
        });
    }
}
