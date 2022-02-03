package com.jiyeon.project.book.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MulticastSocketTest {

    static String participant;
    static InetAddress multiGroup;
    static int portNumber = 5500;


    public static void main(String[] args) {

        String multiGroupAddress = "231.3.3.3";

        try{
            multiGroup = InetAddress.getByName(multiGroupAddress);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        participant = "< jiyeon >";
        MulticastSocketTest multicastSocket = new MulticastSocketTest();
        multicastSocket.new chatReceive().start();
        multicastSocket.new chatSend().start();
    }

    public class chatReceive extends Thread {

        public void run(){
            byte[] bytes = new byte[256];
            try{
                MulticastSocket multicastSocket = new MulticastSocket(portNumber);
                multicastSocket.joinGroup(multiGroup);

                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                multicastSocket.receive(datagramPacket);

                String receivedMessage = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("receivedMessage >> " + receivedMessage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class chatSend extends Thread {

        public void run(){
            byte[] bytes = new byte[256];
            try{
                BufferedReader message = new BufferedReader(new InputStreamReader(System.in));
                DatagramSocket socket = new DatagramSocket();
                System.out.println("message available");

                String temp = participant + message.readLine();
                bytes = temp.getBytes(StandardCharsets.UTF_8);

                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, multiGroup, portNumber);
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
