package com.example.javachatroom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void startserver() {
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ClientController clientController = new ClientController(socket);
                Thread thread = new Thread(clientController);
                thread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1 = new ServerSocket(7000);
        Server server = new Server(serverSocket1);
        server.startserver();
    }


}
