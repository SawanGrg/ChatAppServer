package com.web.chatAppBackend.config;

import com.web.chatAppBackend.filter.AuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:3000")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {


        // enable a simple memory-based message broker to carry the messages back to the client on destinations prefixed with "/topic"
        //means that messages whose destination starts with "/topic" should be routed to the message broker
        //where message broker broadcasts the message to all connected clients who are subscribed to the particular topic
        registry.enableSimpleBroker("/topic", "/queue");


        // app prefix is used to filter destinations targeting application annotated methods
        // means that messages whose destination starts with "/app" should be routed to message-handling methods (i.e. methods annotated with @MessageMapping)
        registry.setApplicationDestinationPrefixes("/app");

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new AuthenticationFilter());
    }

}
