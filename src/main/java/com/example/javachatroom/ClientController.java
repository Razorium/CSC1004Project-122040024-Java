package com.example.javachatroom;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientController implements Runnable {
    public static ArrayList<ClientController> chatters = new ArrayList<>();
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ClientController(Socket socket){
        try{
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            chatters.add(this);
            System.out.println("A user has joined");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //sends message (that is sent from the sender client to the server) to other clients
    public void sendtoeveryone(Message yes, ClientController clientController){
        for(ClientController clientController1 : chatters){
            try{
                if(clientController1 != clientController){
                    clientController1.objectOutputStream.writeObject(yes);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void run() {
        Message yes;
        try {
            while(socket.isConnected()) {
                yes = (Message) objectInputStream.readObject();
                if (yes != null) {
                    sendtoeveryone(yes, this);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
