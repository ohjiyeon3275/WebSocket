package com.jiyeon.project.sockets;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {

        InetAddress localHost = InetAddress.getLocalHost();

        int serverPort = 5000;

        Socket socket = new Socket(localHost, serverPort);

        try {
            BufferedWriter sender = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader message = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                System.out.println("please input message >> ");

                String sendMessage = message.readLine();

                /**
                 * Params:
                 * str – A String
                 * off – Offset from which to start writing characters
                 * len – Number of characters to write
                 */
                sender.write(sendMessage, 0, sendMessage.length());
                sender.newLine();
                sender.flush();

                System.out.println("completed! tcp port number >> " + serverPort);
                String receivedMessage = receiver.readLine();
                System.out.println("received msg is >> " + receivedMessage);

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
