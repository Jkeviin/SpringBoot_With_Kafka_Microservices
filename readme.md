# Proyecto Kafka con Spring Boot - Configuración y Comandos para Apache Kafka

## 📌 Recursos útiles

- **Página oficial de Spring Boot:** [Start Spring](https://start.spring.io/)
- **Generador de banners ASCII:** [Banner Generator](https://devops.datenkollektiv.de/banner.txt/index.html)
- **Documentación oficial de Apache Kafka:** [Apache Kafka Docs](https://kafka.apache.org/)

---

## 🔶 Comandos para iniciar Zookeeper y Kafka

### ▶️ Iniciar Zookeeper
```sh
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

### ▶️ Iniciar Kafka
```sh
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

---

## 🔄 Administración de Topics en Kafka

### ▶️ Crear un nuevo topic
```sh
.\bin\windows\kafka-topics.bat --create --topic {topic-name} --bootstrap-server {host}:9092
```

### ▶️ Describir los detalles de un topic
```sh
.\bin\windows\kafka-topics.bat --describe --topic {topic-name} --bootstrap-server {host}:9092
```

### ▶️ Listar todos los topics
```sh
.\bin\windows\kafka-topics.bat --list --bootstrap-server {host}:9092
```

---

## 📥📤 Enviar y recibir mensajes en Kafka

### ▶️ Iniciar una consola para consumir mensajes de un topic
```sh
.\bin\windows\kafka-console-consumer.bat --topic {nombreTopic} --bootstrap-server {host}:9092
```

### ▶️ Iniciar una consola para producir mensajes a un topic
```sh
.\bin\windows\kafka-console-producer.bat --broker-list {host}:9092 --topic {topic-name}
```

---
---
---

## 1. Explicación de la Configuración del Proyecto

Este proyecto utiliza Spring Boot y Apache Kafka para la comunicación entre productores y consumidores. Se ha configurado un topic llamado `unProgramadorNace-Topic`, con dos particiones y dos réplicas.

- El productor (`KafkaProviderConfig`) envía mensajes al topic.
- El consumidor (`KafkaConsumerListener`) escucha los mensajes y los procesa.
- El topic (`KafkaTopicConfig`) es configurado dinámicamente al iniciar la aplicación.

## 2. Variables de Entorno y Configuración

Se usa `@Value("${spring.kafka.bootstrap-servers}")` para definir el servidor de Kafka. La configuración se debe agregar en `application.properties` o `application.yml`.

### Configuración en `application.properties`
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=my-group-id
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

## 3. Cómo Ejecutar el Proyecto

### Iniciar la Aplicación Spring Boot

Si usas Maven, puedes iniciar el proyecto con:
```sh
mvn spring-boot:run
```

Si usas Gradle, usa:
```sh
./gradlew bootRun
```

O compila el JAR y ejecútalo:
```sh
mvn clean package
java -jar target/mi-aplicacion.jar
```

## 4. Ejemplo de Uso con el Código del Proyecto

Puedes probar el flujo de mensajes entre el productor y el consumidor utilizando `curl` o herramientas como Postman.

### Enviar un mensaje desde el productor (Ejemplo con `curl`)
```sh
curl -X POST http://localhost:8080/enviar -H "Content-Type: application/json" -d '{"mensaje": "Hola desde Kafka"}'
```

### Ver los mensajes recibidos en la consola del consumidor
Si ejecutaste el consumidor correctamente, deberías ver algo como:
```yaml
Mensaje recibido, el mensaje es: Hola desde Kafka
```


