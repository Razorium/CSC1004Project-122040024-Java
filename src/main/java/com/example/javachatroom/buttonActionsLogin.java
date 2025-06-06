package com.example.javachatroom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class buttonActionsLogin implements Initializable {
    @FXML
    private Button loginbutton;
    @FXML
    private Button registerbutton;
    @FXML
    private TextField usernamelogin;
    @FXML
    private TextField passwordlogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        registerbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneChanging.changescene(event, "register-view.fxml", "Registration", null, null);
            }
        });
        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String input1 = usernamelogin.getText();
                String input2 = passwordlogin.getText();
                LogInController.LoginUser(event, input1, input2);
            }
        });
    }
}
