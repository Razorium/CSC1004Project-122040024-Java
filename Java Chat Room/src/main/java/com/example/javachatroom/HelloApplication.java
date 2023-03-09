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
            //show1();
            show2();
        } catch(Exception e) {
            System.out.println("Errors");
            e.printStackTrace();
        }
    }

    public void show1(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,400,400);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void show2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        JDBCstuffs loginViewController = fxmlLoader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}