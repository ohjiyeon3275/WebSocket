package com.jiyeon.project.book.chatting.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;


public class VectorClient extends Thread {

    DataInputStream inputStream;
    DataOutputStream outputStream;
    Socket socket;

    public VectorClient(Socket socket){
        this.socket = socket;
        try{
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        String receiver;

        while(true){
            try{
                receiver = inputStream.readUTF();

            } catch (IOException e) {
                e.printStackTrace();
                quit(this);
                return ;
            }

            int tmp = receiver.indexOf("Quit");
            if(tmp >= 0 ){
                String nickName = receiver.substring(0, tmp);
                send(nickName + " leave the room");
                quit(this);
                return ;
            }else{
                send(receiver);
            }
        }
    }

    public void send(String receivers) {
        Vector vector = ChattingServer.vectors;

        try {
            for (int i = 0; i < vector.size(); i++) {
                VectorClient vectorClient = (VectorClient) vector.elementAt(i);
                vectorClient.outputStream.writeUTF(receivers);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void quit(VectorClient clients){

        Vector vector = ChattingServer.vectors;
        Socket socketClient = ChattingServer.socketClient;
        int tmp = vector.indexOf(clients);
        if(tmp >= 0){
            vector.removeElementAt(tmp);
            try{
                clients.inputStream.close();
                clients.outputStream.close();
                socketClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(">>> current user number is " + vector.size());


        }else{
            System.out.println(">>> there is no vector to quit(); ");
        }


    }

}
