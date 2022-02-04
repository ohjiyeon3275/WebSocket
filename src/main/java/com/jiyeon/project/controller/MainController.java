package com.jiyeon.project.controller;

import com.jiyeon.project.dto.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MainController {

    @MessageMapping("/msgee")
    @SendTo("/sockets/msge")
    public ResponseMessage getMessage(Message message) throws InterruptedException {

        Thread.sleep(1000); // 1 sec.

        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-msgee")
    @SendToUser("/sockets/private-msge")
    public ResponseMessage getPrivateMessage(Message message,
                                             Principal principal) throws InterruptedException {

        Thread.sleep(1000);

        return new ResponseMessage(HtmlUtils
                .htmlEscape("sending private message" + principal.getName() + message.getMessageContent()));

    }
}
