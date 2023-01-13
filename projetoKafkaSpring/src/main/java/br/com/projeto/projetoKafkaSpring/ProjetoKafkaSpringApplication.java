package br.com.projeto.projetoKafkaSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ProjetoKafkaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoKafkaSpringApplication.class, args);

		System.out.println("\n----------------------");
		System.out.println("ENTROU NA APLICAÇÃO!!!");
		System.out.println("----------------------\n");

	}

}
