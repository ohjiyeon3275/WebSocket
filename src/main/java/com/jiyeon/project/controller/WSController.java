package com.jiyeon.project.controller;

import com.jiyeon.project.dto.Message;
import com.jiyeon.project.service.WSService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WSController {

    private final WSService wsService;

    @PostMapping("/send-msg")
    public void sendMessage(@RequestBody Message message){

        wsService.notifyFrontend(message.getMessageContent());
    }


    @PostMapping("/send-private-msg/{id}")
    public void sendPrivateMessage(@PathVariable String id,
                            @RequestBody Message message){

        wsService.notifyUser(id, message.getMessageContent());
    }


}
