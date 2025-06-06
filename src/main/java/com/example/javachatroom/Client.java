package com.example.javachatroom;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private ObjectOutputStream objectOutputStream;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private String username;
    private MainPageController controller;

    public Client(MainPageController controller){
            this.controller = controller;
    }
    public void sendTextMessage(Message yes) throws IOException{
        objectOutputStream.writeObject(yes);
    }
    public void sendAudioMessage(byte[] audio, String username3) throws IOException{
        Message yes = new Message(username3);
        yes.addtext("Sent a voice message!");
        yes.setType("audio");
        yes.addVoice(audio);
        objectOutputStream.writeObject(yes);
    }
    public void sendImagemessage(Message yes) throws IOException{
        objectOutputStream.writeObject(yes);
    }

    class Listentomessages implements Runnable{
        @Override
        public void run() {
            try{
                while (socket.isConnected()) {
                    Message yes = (Message) objectInputStream.readObject();
                    if (yes != null) {
                        controller.addMessage(yes);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 7000);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Listentomessages listentomessages = new Listentomessages();
            Thread thread = new Thread(listentomessages);
            thread.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
