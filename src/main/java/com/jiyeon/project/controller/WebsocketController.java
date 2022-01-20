package com.jiyeon.project.controller;

import com.jiyeon.project.dto.Message;
import com.jiyeon.project.service.WebsocketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebsocketController {

    private final WebsocketService websocketService;

    @PostMapping("/send")
    public void sendMsg(@RequestBody Message message){
        websocketService.notify(message.getMessageContents());
    }

}
