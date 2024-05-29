package com.web.chatAppBackend.model;

import com.web.chatAppBackend.enums.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@Component
public class ChatMessage {

    private String id;
    private String content;
    private String sender;
    private String receiver;
    private MessageType type;
    private LocalDateTime timestamp;

    public ChatMessage() {
    }

    public ChatMessage(String content, String sender,
                       String receiver,
                       MessageType type, LocalDateTime timestamp) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.timestamp = timestamp;
    }


}
