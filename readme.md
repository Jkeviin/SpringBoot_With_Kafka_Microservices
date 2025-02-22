# README - Configuración y Comandos para Apache Kafka

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

