package com.jiyeon.project.service;

import com.jiyeon.project.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    //global
    public void sendGlobal(){
        ResponseMessage message = new ResponseMessage("this is global message");

        simpMessagingTemplate.convertAndSend("/sockets/global-notification", message);
    }


    //private

    public void sendPrivate(String userId){
        ResponseMessage message = new ResponseMessage("this is private message");

        simpMessagingTemplate.convertAndSendToUser(userId, "/sockets/private-notification", message);
    }



}
