package com.jxy.config.mqConfig;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;

public class MqConfig {
   /* @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("118.24.188.79");
        return factory;
    }*/
    /*public FanoutExchange fanoutExchange(ConnectionFactory factory){
        FanoutExchange fanoutExchange=new FanoutExchange("fanoutExchange");

    }*/
}
