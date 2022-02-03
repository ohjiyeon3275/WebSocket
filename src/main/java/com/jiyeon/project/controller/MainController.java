package com.jiyeon.project.controller;

import com.jiyeon.project.dto.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MainController {

    @MessageMapping("/msgee")
    @SendTo("/sockets/msge")
    public ResponseMessage getMessage(Message message) throws InterruptedException {

        System.out.println("컨트롤러를 타나?");
        Thread.sleep(1000); // 1 sec.

        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));

    }
}
