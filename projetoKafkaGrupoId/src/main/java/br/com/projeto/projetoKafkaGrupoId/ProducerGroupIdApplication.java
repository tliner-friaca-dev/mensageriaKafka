package br.com.projeto.projetoKafkaGrupoId;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProducerGroupIdApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SpringApplication.run(ProducerGroupIdApplication.class, args);

		KafkaProducer producer = new KafkaProducer<String, String>(properties());

		// ProducerRecord record = new ProducerRecord("usuarios.v1.usuario-criado", "chave-usuario", "nome-Ronaldo");

		Callback callback = (data, error) -> {
			if(error != null) {
				error.printStackTrace();
				return;
			}

			System.out.println("\nMensagem publicada com sucesso:");
			System.out.println("Partição: " + data.partition());
			System.out.println(data.offset());
			System.out.println(data.topic());

		};

		for (Integer i = 0; i < 2; i++) {
			ProducerRecord record = new ProducerRecord("grupos.v1.grupo-criado", "chave-grupo"+i, "valor-"+i);
			producer.send(record, callback).get();
		}

	}

	private static Properties properties() {

		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		return properties;
	}

}
