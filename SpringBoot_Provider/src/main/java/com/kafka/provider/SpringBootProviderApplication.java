package com.kafka.provider;

// Importaciones necesarias para la configuración de una aplicación Spring Boot con Kafka
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Clase principal de la aplicación Spring Boot para el proveedor de Kafka.
 *
 * Esta clase inicia la aplicación y envía un mensaje a un topic de Kafka
 * al arrancar, utilizando un CommandLineRunner.
 */
@SpringBootApplication
public class SpringBootProviderApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 *
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProviderApplication.class, args);
	}

	/**
	 * Define un CommandLineRunner que se ejecuta al iniciar la aplicación.
	 *
	 * Este método utiliza un KafkaTemplate para enviar un mensaje al topic "unProgramadorNace-Topic"
	 * inmediatamente después de que la aplicación haya arrancado.
	 *
	 * @param kafkaTemplate Plantilla de Kafka utilizada para enviar mensajes.
	 * @return Un CommandLineRunner que envía un mensaje a Kafka.
	 */
	@Bean
	CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			// Envía un mensaje al topic "unProgramadorNace-Topic" cuando la aplicación inicia
			kafkaTemplate.send("unProgramadorNace-Topic", "Resultado final de Kafka");
		};
	}
}
