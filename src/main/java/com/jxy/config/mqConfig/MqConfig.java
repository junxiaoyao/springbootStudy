package com.jxy.config.mqConfig;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.SimpleMessageConverter;

@Configuration
public class MqConfig {

    @Value("${spring.activemq.broker-url}")
    private String serverUrl;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory factory, Queue queue) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(factory);
        jmsTemplate.setDefaultDestination(queue);
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(ActiveMQConnectionFactory factory,
        Queue queue, MessageListener messageListener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setDestination(queue);
        container.setMessageListener(messageListener);
        return container;
    }

    @Bean
    public MessageListener messageListener() {
        return (message -> {
            if (message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("receive:" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(serverUrl);
        return connectionFactory;
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }
}
