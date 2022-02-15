package com.jiyeon.project.service;

import com.jiyeon.project.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WSService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    public void notifyFrontend(String message){
        ResponseMessage responseMessage = new ResponseMessage(message);

        notificationService.sendGlobal();
        simpMessagingTemplate.convertAndSend("/sockets/msge", responseMessage);
    }

    public void notifyUser(String id, String message){
        ResponseMessage responseMessage = new ResponseMessage(message);

        notificationService.sendPrivate(id);
        simpMessagingTemplate.convertAndSendToUser(id,"/sockets/private-msge", responseMessage);
    }
}
