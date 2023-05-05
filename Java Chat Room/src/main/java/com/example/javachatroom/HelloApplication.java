package com.example.javachatroom;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class HelloApplication extends Application {
    public Stage primaryStage;
    @FXML
    public BorderPane bp;
    @FXML
    public StackPane sp;
    @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage=primaryStage;
            show2();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void show2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        buttonActionsLogin buttonActions = fxmlLoader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}