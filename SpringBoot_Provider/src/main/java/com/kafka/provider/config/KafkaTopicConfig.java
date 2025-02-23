package com.kafka.provider.config;

// Importaciones necesarias para la configuración de topics en Kafka con Spring Boot
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de configuración para la creación de topics en Kafka.
 *
 * Define un topic con configuraciones específicas que controlan su comportamiento,
 * como la política de limpieza, el tiempo de retención y el tamaño de los segmentos.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Mapa estático que almacena configuraciones personalizadas para el topic.
     *
     * Estas configuraciones definen cómo Kafka manejará los datos dentro del topic,
     * como la política de eliminación, el tiempo de retención de mensajes, y el tamaño máximo de mensajes.
     */
    private static final Map<String, String> configurations = new HashMap<>();

    static {
        // Define la política de limpieza del topic.
        // CLEANUP_POLICY_DELETE indica que los mensajes antiguos serán eliminados según la política de retención.
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);

        // Configura el tiempo de retención de los mensajes en milisegundos (8640000 ms = 2.4 horas).
        // Una vez que expira este tiempo, los mensajes pueden ser eliminados según la política de limpieza.
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "8640000");

        // Define el tamaño máximo de un segmento de log en bytes (1 GB = 1073741824 bytes).
        // Cuando un segmento alcanza este tamaño, Kafka crea un nuevo segmento.
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");

        // Configura el tamaño máximo permitido para un mensaje en bytes (aproximadamente 1 MB).
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    }

    /**
     * Define y registra un nuevo topic en Kafka con configuraciones personalizadas.
     *
     * Este método usa TopicBuilder para crear un topic con:
     * - Nombre "unProgramadorNace-Topic"
     * - 2 particiones (permite distribuir la carga de trabajo entre consumidores)
     * - 2 réplicas (garantiza disponibilidad en caso de falla de un nodo)
     * - Configuraciones personalizadas del mapa "configurations"
     *
     * @return Instancia de NewTopic con la configuración definida.
     */
    @Bean
    public NewTopic generateTopic() {
        return TopicBuilder.name("unProgramadorNace-Topic")
                .partitions(2) // Define el número de particiones del topic
                .replicas(2) // Define el número de réplicas del topic para tolerancia a fallos
                .configs(configurations) // Aplica las configuraciones definidas previamente
                .build();
    }
}
