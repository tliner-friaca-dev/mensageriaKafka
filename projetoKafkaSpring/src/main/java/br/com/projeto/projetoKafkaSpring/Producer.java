package br.com.projeto.projetoKafkaSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/producer/{message}")
    public void producer(@PathVariable("message") String message) {
        System.out.println(message);
        this.kafkaTemplate.send("mesagens.v1.mensagem.criada", message);
    }
    
}
