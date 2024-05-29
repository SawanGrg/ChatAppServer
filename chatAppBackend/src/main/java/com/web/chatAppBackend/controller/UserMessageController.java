package com.web.chatAppBackend.controller;

import com.web.chatAppBackend.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UserMessageController {

    private static final Logger logger = LoggerFactory.getLogger(UserMessageController.class);

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UserMessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendPrivateMessage(ChatMessage message) {
        logger.info("Received message to send privately: {}", message);

        // Ensure consistent order of tokens in the combined token
        String combinedToken = message.getSender().compareTo(message.getReceiver()) < 0 ?
                message.getSender() + "_" + message.getReceiver() :
                message.getReceiver() + "_" + message.getSender();

        String destination = "/queue/messages/" + combinedToken;

        // Send the message to the specific user using @SendToUser annotation
        messagingTemplate.convertAndSend(destination, message);

        logger.info("Message sent privately to combined token '{}'", combinedToken);
    }
}
