package com.daniil.wikikafkaproducer.utils;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikiChangesHandler implements BackgroundEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(WikiChangesHandler.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public WikiChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {}

    @Override
    public void onClosed() throws Exception {}

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        logger.info("Received message: {}", messageEvent.getData());

        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {}

    @Override
    public void onError(Throwable throwable) {}
}
