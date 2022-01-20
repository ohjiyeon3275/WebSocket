package com.jiyeon.project.service;

import com.jiyeon.project.dto.ResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService {

    private SimpMessagingTemplate messagingTemplate;

    public void notify(String message){
        ResponseMessage responseMessage = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/sockets/msg", responseMessage);
    }

}
