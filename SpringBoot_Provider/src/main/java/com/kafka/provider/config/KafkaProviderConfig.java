package com.kafka.provider.config;

// Importaciones necesarias para la configuración del productor de Kafka y su integración con Spring Boot
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de configuración para el productor de Kafka.
 *
 * Define los parámetros esenciales que permiten que una aplicación Spring Boot
 * pueda enviar mensajes a un topic de Kafka.
 */
@Configuration
public class KafkaProviderConfig {

    /**
     * Dirección del servidor Kafka. Este valor es inyectado desde el archivo
     * de configuración application.properties o application.yml usando @Value.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    /**
     * Configura las propiedades esenciales del productor de Kafka.
     *
     * Estas propiedades definen cómo se serializan los mensajes y cómo se conecta al clúster de Kafka.
     *
     * @return Mapa de configuraciones para el productor de Kafka.
     */
    public Map<String, Object> producerConfig(){
        Map<String, Object> properties = new HashMap<>();

        // Configuración del servidor Kafka al que se conectará el productor
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);

        // Definición del serializador para la clave del mensaje enviado
        // Kafka maneja los datos en formato binario (bytes), por lo que es necesario convertir
        // las claves de los mensajes de String a bytes antes de enviarlos.
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Definición del serializador para el valor del mensaje enviado
        // Al igual que con la clave, el contenido del mensaje debe ser transformado en bytes.
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    /**
     * Define la fábrica de productores para Kafka.
     *
     * Un ProducerFactory es responsable de crear instancias de productores
     * que enviarán mensajes a Kafka con la configuración definida en producerConfig().
     *
     * @return Una instancia de ProducerFactory<String, String>.
     */
    @Bean
    public ProducerFactory<String, String> providerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * Configura una plantilla de Kafka para enviar mensajes.
     *
     * KafkaTemplate simplifica la interacción con Kafka, permitiendo enviar mensajes
     * de manera sencilla sin necesidad de interactuar directamente con el ProducerFactory.
     *
     * @param producerFactory Fábrica de productores de Kafka.
     * @return Una instancia de KafkaTemplate lista para enviar mensajes a Kafka.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
