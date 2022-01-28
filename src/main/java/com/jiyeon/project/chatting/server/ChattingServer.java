package com.jiyeon.project.chatting.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChattingServer {

    public static ServerSocket serverSocket = null;
    public static Socket socketClient = null;
    public static Vector vectors = new Vector();

    public ChattingServer(String[] args) {
        int serverPort = 5200;
        try{
            serverSocket = new ServerSocket(serverPort);
            System.out.println(">>> server is running >>> ");
            while(true){
                socketClient = serverSocket.accept();
                VectorClient vectorClient = new VectorClient(socketClient);
                vectors.addElement(vectorClient);

                System.out.println(">>> current users >>> " + vectors.size());

                vectorClient.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ChattingServer(args);
    }
}
