package com.jiyeon.project.book.chatting.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.jiyeon.project.book.chatting.client.ChattingClient.*;


public class ActionListenerUtil {

    public static class OnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(ChattingClient.on == false){
                try{
                    sockets  = new Socket("localhost", portNumber);
                    inputStream = new DataInputStream(new BufferedInputStream(sockets.getInputStream()));
                    outputStream = new DataOutputStream(new BufferedOutputStream(sockets.getOutputStream()));

                    thread = new Thread();
                    thread.start();

                    outputStream.writeUTF("<< " + uid.getText() + ">> joined");
                    outputStream.flush();

                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    return;
                }

                on = true;

            }else{
                msgOut.append("already connected ...");
            }
        }
    }


    public static class MsgListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            String tmp = null;

            tmp = "<< "+ uid.getText() + ">>" + msg.getText();
            try{
                outputStream.writeUTF(tmp);
                outputStream.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    public static class OffListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            on = false;
            try{
                outputStream.writeUTF("<< " + uid.getText() + ">> left chatting room");
                outputStream.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            try{
                outputStream.close();
                inputStream.close();
                sockets.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
