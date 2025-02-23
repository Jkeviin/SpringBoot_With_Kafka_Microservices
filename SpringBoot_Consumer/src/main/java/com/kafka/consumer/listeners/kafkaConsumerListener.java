package com.kafka.consumer.listeners;

// Importaciones necesarias para la configuración del consumidor de Kafka
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Clase encargada de escuchar mensajes de un topic de Kafka.
 *
 * Esta clase actúa como un consumidor de Kafka, recibiendo y procesando mensajes
 * publicados en el topic "unProgramadorNace-Topic".
 */
@Configuration
public class kafkaConsumerListener {

    // Logger para registrar los mensajes recibidos y eventos del consumidor
    private static final Logger LOGGER = LoggerFactory.getLogger(kafkaConsumerListener.class);

    /**
     * Método que escucha y procesa mensajes provenientes de Kafka.
     *
     * La anotación @KafkaListener permite a Spring Boot suscribir este método
     * a un topic de Kafka. Cada vez que se recibe un mensaje en el topic "unProgramadorNace-Topic",
     * este método será invocado automáticamente.
     *
     * @param message Mensaje recibido desde el topic de Kafka.
     */
    @KafkaListener(topics = {"unProgramadorNace-Topic"}, groupId = "my-group-id")
    public void listener(String message) {
        // Registra el mensaje recibido en los logs de la aplicación
        LOGGER.info("Mensaje recibido, el mensaje es: " + message);
    }
}
