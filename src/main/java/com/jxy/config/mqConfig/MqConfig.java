package com.jxy.config.mqConfig;

import com.rabbitmq.client.ConnectionFactory;

public class MqConfig {
    public ConnectionFactory connectionFactory(){
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("118.24.188.79");
        return factory;
    }
}
