package com.jiyeon.project.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDatagramPacket {

    public static void main(String[] args) {

        byte tempMessage[] = new byte[256];
        int portNumber = 5100;

        try{
//            InetAddress localHost = InetAddress.getLocalHost(); 아래와 같다.
            InetAddress localhost = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();

            BufferedReader bufferMessage = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("input message here >> ");
            String sendMessage = bufferMessage.readLine();
            tempMessage = sendMessage.getBytes();

            //송신용 DatagramPacket (인자가 4개)
            DatagramPacket sendDatagramPacket = new DatagramPacket(tempMessage, tempMessage.length, localhost, portNumber);
            socket.send(sendDatagramPacket);
            System.out.println("send completed ... udp server port number is " + portNumber);

            //수신용 DatagramPacket (인자값이 2개)
            DatagramPacket receiveDatagramPacket = new DatagramPacket(tempMessage, tempMessage.length);
            socket.receive(receiveDatagramPacket);

            String serverMessage = new String(tempMessage);
            System.out.println("server message is >> " + serverMessage);


            /**
             * UDP통신은 송신은 되는데 수신이 안된다
             * system.in 시에
             * 포트번호를 netstat으로 찍어봐도 listen 하는 port는 5100번이 없다
             * mac or java? -> udp부분은 python으로 다시 시도해볼것
             */

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
