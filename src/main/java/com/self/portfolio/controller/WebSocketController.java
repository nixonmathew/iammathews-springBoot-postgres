package com.self.portfolio.controller;

import com.self.portfolio.dto.Greeting;
import com.self.portfolio.dto.HelloMessage;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
@RequestMapping("/api")
public class WebSocketController {

    // private final SimpMessagingTemplate template;

    // @Autowired
    // WebSocketController(SimpMessagingTemplate template) {
    //     this.template = template;
    // }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}