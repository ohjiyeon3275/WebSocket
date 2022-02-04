package com.jiyeon.project.controller;

import com.jiyeon.project.dto.Message;
import com.jiyeon.project.service.WSService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WSController {

    private final WSService wsService;

    @PostMapping("/send-msg")
    public void sendMessage(@RequestBody Message message){
        System.out.println(">>??? controller?? ");
        wsService.notifyFrontend(message.getMessageContent());
        System.out.println(">>> conrtoller ");
    }
}
