package com.jiyeon.project.book.chatting.client;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChattingClient implements Runnable {

    public static DataInputStream inputStream;
    public static DataOutputStream outputStream;
    public static Socket sockets;
    public static final int portNumber = 5200;
    public static Thread thread = null;

    public static TextField uid;
    public static TextField msg;
    public static Button buttonOn, buttonOff;
    public static TextArea msgOut;
    public static boolean on = false;

    public void init(){
        Panel panel = new Panel();

        Label username = new Label("cute Ji Yeon");
        uid = new TextField(" 123 ");
        buttonOn = new Button("Start");
        buttonOff = new Button("End");

        panel.add(username);
        panel.add(uid);
        panel.add(buttonOn);
        panel.add(buttonOff);

        buttonOn.addActionListener(new ActionListenerUtil.OnListener());
        buttonOff.addActionListener(new ActionListenerUtil.OffListener());

        msgOut = new TextArea();
        msgOut.setEditable(false);
        msg = new TextField();
        msg.addActionListener(new ActionListenerUtil.MsgListener());

        BorderLayout borderLayout = new BorderLayout();
        borderLayout.addLayoutComponent(panel, BorderLayout.NORTH);
        borderLayout.addLayoutComponent(msgOut, BorderLayout.CENTER);
        borderLayout.addLayoutComponent(msg, BorderLayout.SOUTH);

    }


    @Override
    public void run() {
        String tmp = null;
        while(on){
            try{
                tmp = inputStream.readUTF() + "\n";

            } catch (IOException e) {
                e.printStackTrace();
                msgOut.append(tmp);
            }
        }
    }

    public static void main(String[] args) {

        ChattingClient client = new ChattingClient();
        client.init();
        client.run();
    }
}
