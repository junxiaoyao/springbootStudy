package com.jxy.config.websocketConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

public class MyHandTextMessag extends AbstractWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyHandTextMessag.class);

    @Override
    protected void handleTextMessage(WebSocketSession socketSession, TextMessage message)
            throws InterruptedException, IOException {
        logger.info("Received message: " + message.getPayload());
        Thread.sleep(100);
        socketSession.sendMessage(new TextMessage("ok!i receive your message:" + message.getPayload()));
    }

}
