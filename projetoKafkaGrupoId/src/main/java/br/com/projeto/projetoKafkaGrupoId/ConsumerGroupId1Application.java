package br.com.projeto.projetoKafkaGrupoId;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerGroupId1Application {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SpringApplication app = new SpringApplication(ConsumerGroupId1Application.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8081"));
        app.run(args);

		var consumer = new KafkaConsumer<String, String>(properties());

		consumer.subscribe(Collections.singletonList("grupos.v1.grupo-criado"));

		while (true) {

			var records = consumer.poll(Duration.ofMillis(100));

			for (var record : records) {

				System.out.println("\nNova mensagem recebida: ");
				System.out.println((record.key()));
				System.out.println((record.value()));
				System.out.println((record.partition()));
				System.out.println((record.offset()));
				
			}
			
		}

	}

	private static Properties properties() {

		Properties properties = new Properties();

		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumidor-1");

		return properties;
	}

}
