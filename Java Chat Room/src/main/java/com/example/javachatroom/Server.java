package com.example.javachatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void StartServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A CLIENT HAS SUCCESFULLY CONNECTED");
                ClientHandler clientHandler = new ClientHandler;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
