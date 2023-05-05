package com.example.javachatroom;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainPageController implements Initializable {
    private static byte[] suara;
    private String username;
    @FXML
    private TextField messagefield;
    @FXML
    private Button sendbutton;
    @FXML
    private ScrollPane scrollpaneforchat;
    @FXML
    private VBox vboxforchat;
    @FXML
    private Button voicebutton;

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = new Client(this);
        Thread thread = new Thread(client);
        thread.start();
        System.out.println("Joined the chatroom successfully");

        voicebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(VoiceUtil.isitrecording()){
                    VoiceUtil.setRecording(false);
                    Message yes = null;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        displayaudiomessage(yes, username, Pos.CENTER_RIGHT);
                    } catch (LineUnavailableException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        client.sendAudioMessage(suara, username);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        VoiceActions.record();
                    } catch (LineUnavailableException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        sendbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String message = messagefield.getText();
                messagefield.clear();
                Message yes = new Message(username);
                yes.setType("text");
                yes.addtext(message);
                if(!message.isEmpty()){
                    displaytextmessage(message, username, Pos.CENTER_RIGHT);
                    try{
                        client.sendTextMessage(yes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void displayaudiomessage(Message message, String parausername, Pos alignment) throws LineUnavailableException {
        VBox vBox = new VBox();
        HBox textline = new HBox();
        HBox userlabelline = new HBox();
        Label userlabel = new Label();

        vBox.setPadding(new Insets(5));
        userlabel.setText(parausername);
        userlabel.setPadding(new Insets(5));
        userlabel.setStyle("-fx-text-fill: #ffffff");
        userlabelline.getChildren().add(userlabel);
        userlabelline.setAlignment(alignment);
        vBox.getChildren().add(userlabelline);

        TextFlow bubble = new TextFlow(new Text("Sent a voice message"));
        if(message != null) VoiceActions.playback(message.returnvoice());
        bubble.setLineSpacing(5);
        bubble.setMaxWidth(400);
        bubble.setPadding(new Insets(3));
        if(parausername.equals(this.username)){
            bubble.setStyle("-fx-background-color: #f8d5a6" + "; -fx-background-radius: 10px");
        }
        else{
            bubble.setStyle("-fx-background-color: #ffffff" + "; -fx-background-radius: 10px");
        }
        textline.getChildren().add(bubble);
        textline.setAlignment(alignment);
        vBox.getChildren().add(textline);
        vboxforchat.getChildren().add(vBox);
    }

    public void displaytextmessage(String message, String parausername, Pos alignment){
        VBox vBox = new VBox();
        HBox textline = new HBox();
        HBox userlabelline = new HBox();
        Label userlabel = new Label();

        vBox.setPadding(new Insets(5));
        userlabel.setText(parausername);
        userlabel.setPadding(new Insets(5));
        userlabel.setStyle("-fx-text-fill: #ffffff");
        userlabelline.getChildren().add(userlabel);
        userlabelline.setAlignment(alignment);
        vBox.getChildren().add(userlabelline);

        TextFlow bubble = new TextFlow(new Text(message));
        bubble.setLineSpacing(5);
        bubble.setMaxWidth(400);
        bubble.setPadding(new Insets(3));
        if(parausername.equals(this.username)){
            bubble.setStyle("-fx-background-color: #f8d5a6" + "; -fx-background-radius: 10px");
        }
        else{
            bubble.setStyle("-fx-background-color: #ffffff" + "; -fx-background-radius: 10px");
        }
        textline.getChildren().add(bubble);
        textline.setAlignment(alignment);
        vBox.getChildren().add(textline);
        vboxforchat.getChildren().add(vBox);
    }
    public static void voice(byte[] audio){
        suara = audio;
    }
    public void addMessage(Message message){
        if(message.getType().equals("text")) {
            String username1 = message.returnusername();
            String texthoho = message.returntext();
            Platform.runLater(() -> displaytextmessage(texthoho, username1, Pos.CENTER_LEFT));
        } else if(message.getType().equals("audio")){
            String username2 = message.returnusername();
            Platform.runLater(() -> {
                try {
                    displayaudiomessage(message, username2, Pos.CENTER_LEFT);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
