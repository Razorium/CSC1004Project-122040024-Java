package com.example.javachatroom;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneChanging {
    public static void changescene(ActionEvent event, String fxmlfile, String title, String username, String password){
        Parent root = null;

        if(username != null && password!= null){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(sceneChanging.class.getResource(fxmlfile));
                root = fxmlLoader.load();
                buttonActionsLogin logInController = fxmlLoader.getController();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(sceneChanging.class.getResource(fxmlfile));
                root = fxmlLoader.load();
                buttonActionsRegister buttonActionsRegister = fxmlLoader.getController();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 640, 360));
        stage.show();
    }

    public static void changescenetomain(ActionEvent event, String fxmlfile, String title){
        Parent root = null;

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(sceneChanging.class.getResource(fxmlfile));
            root = fxmlLoader.load();
            ClientPageController clientPageController = fxmlLoader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 640, 360));
        stage.show();
    }
}
