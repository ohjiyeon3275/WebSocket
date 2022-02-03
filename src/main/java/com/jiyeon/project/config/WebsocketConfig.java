package com.jiyeon.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        /**
         * Enable a simple message broker and configure one or more prefixes to filter destinations targeting the broker
         * (e.g. destinations prefixed with "/topic").
         */
        registry.enableSimpleBroker("/sockets");

        /**
         * Configure one or more prefixes to filter destinations targeting application annotated methods.
         * For example destinations prefixed with "/app" may be processed by annotated methods while other destinations may target the message broker (e.g. "/topic", "/queue").
         * When messages are processed, the matching prefix is removed from the destination in order to form the lookup path. This means annotations should not contain the destination prefix.
         * Prefixes that do not have a trailing slash will have one automatically appended.
         */
        registry.setApplicationDestinationPrefixes("/jy");

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/jy-websocket").withSockJS();
    }
}
