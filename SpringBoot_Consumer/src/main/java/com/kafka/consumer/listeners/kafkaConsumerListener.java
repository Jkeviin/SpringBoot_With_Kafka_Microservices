package com.kafka.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
public class kafkaConsumerListener {
    private Logger LOGGER = LoggerFactory.getLogger(kafkaConsumerListener.class);

    @KafkaListener(topics = {"unProgramadorNace-Topic"}, groupId = "my-group-id")
    public void listener(String message){
        LOGGER.info("Mensaje recibido, el mensaje es: " + message);


    }
}
