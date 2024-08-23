package com.daniil.wikikafkaproducer;

import com.daniil.wikikafkaproducer.kafka.WikiChangeProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WikiKafkaProducerApplication implements CommandLineRunner {

    public WikiKafkaProducerApplication(WikiChangeProducer wikiChangeProducer) {
        this.wikiChangeProducer = wikiChangeProducer;
    }

    public static void main(String[] args) {
        SpringApplication.run(WikiKafkaProducerApplication.class, args);
    }

    private final WikiChangeProducer wikiChangeProducer;

    @Override
    public void run(String... args) throws Exception {
        wikiChangeProducer.send();
    }
}
