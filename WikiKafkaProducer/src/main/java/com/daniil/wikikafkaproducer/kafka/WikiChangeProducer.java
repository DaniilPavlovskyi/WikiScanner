package com.daniil.wikikafkaproducer.kafka;

import com.daniil.wikikafkaproducer.utils.WikiChangesHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikiChangeProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikiChangeProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    @Value("${wiki.url}")
    private String url;

    public WikiChangeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send() throws InterruptedException {

        BackgroundEventHandler eventHandler = new WikiChangesHandler(kafkaTemplate, topic);
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler,
                new EventSource.Builder(URI.create(url)));

        try(BackgroundEventSource eventSource = builder.build()){
            eventSource.start();
            TimeUnit.MINUTES.sleep(10);
        }

    }
}
