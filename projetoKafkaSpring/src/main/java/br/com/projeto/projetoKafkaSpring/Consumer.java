package br.com.projeto.projetoKafkaSpring;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "mesagens.v1.mensagem.criada", groupId = "consumidor-1")
    public void consumer(String mensagem) {
        
        System.out.println("\nMensagem recebida: ");
        System.out.println(mensagem);

    }
    
}
