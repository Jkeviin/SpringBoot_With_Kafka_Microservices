package com.kafka.consumer.config;

// Importaciones necesarias para la configuración de Kafka y su integración con Spring Boot
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase de configuración para el consumidor de Kafka.
 *
 * Esta clase define las configuraciones necesarias para que un consumidor de Kafka
 * pueda recibir y procesar mensajes de un topic de Kafka de manera concurrente.
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * Dirección del servidor Kafka. Este valor es inyectado desde el archivo
     * de configuración application.properties o application.yml usando @Value.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    /**
     * Configura las propiedades esenciales del consumidor de Kafka.
     *
     * Estas propiedades son necesarias para establecer la conexión con Kafka y definir
     * cómo los mensajes serán deserializados antes de ser procesados por el consumidor.
     *
     * @return Mapa de configuraciones para el consumidor de Kafka.
     */
    public Map<String, Object> consumerConfig(){
        Map<String, Object> properties = new HashMap<>();

        // Configuración del servidor Kafka al que se conectará el consumidor
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);

        // Definición del deserializador para la clave del mensaje recibido
        // Kafka transporta mensajes en formato binario (bytes) para optimizar la transferencia de datos.
        // Para poder trabajar con estos datos en la aplicación, es necesario deserializarlos a un formato comprensible,
        // como una cadena de texto (String) o un objeto JSON.
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Definición del deserializador para el valor del mensaje
        // Esto permite que el contenido del mensaje se convierta de bytes a String automáticamente
        // antes de ser procesado por el consumidor de Kafka.
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return properties;
    }

    /**
     * Define la fábrica de consumidores para Kafka.
     *
     * El ConsumerFactory es responsable de crear instancias de consumidores
     * con la configuración definida en consumerConfig().
     *
     * @return Una instancia de ConsumerFactory<String, String>.
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Configura un contenedor de escucha para consumidores de Kafka.
     *
     * El KafkaListenerContainerFactory permite que múltiples consumidores
     * operen de manera concurrente, procesando mensajes de Kafka en paralelo.
     *
     * @return Una instancia de KafkaListenerContainerFactory para manejar los mensajes de Kafka.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> consumer(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        // Se asocia el ConsumerFactory para que los consumidores sean creados con la configuración adecuada
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
