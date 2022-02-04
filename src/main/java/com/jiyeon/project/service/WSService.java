package com.jiyeon.project.service;

import com.jiyeon.project.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WSService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void notifyFrontend(String message){
        ResponseMessage responseMessage = new ResponseMessage(message);

        simpMessagingTemplate.convertAndSend("/sockets/msge", responseMessage);
    }
}
