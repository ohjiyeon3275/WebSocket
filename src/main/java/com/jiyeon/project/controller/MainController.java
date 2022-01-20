package com.jiyeon.project.controller;

import com.jiyeon.project.dto.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MainController {

    @MessageMapping("/msg")
    @SendTo("/sockets/msg")
    public ResponseMessage getMessage(Message message) throws InterruptedException {

        Thread.sleep(1000);
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContents()));

    }

}
