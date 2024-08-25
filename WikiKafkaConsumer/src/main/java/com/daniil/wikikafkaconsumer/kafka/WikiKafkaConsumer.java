package com.daniil.wikikafkaconsumer.kafka;

import com.daniil.wikikafkaconsumer.entity.WikiRecord;
import com.daniil.wikikafkaconsumer.repository.WikiRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikiKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(WikiKafkaConsumer.class);

    private final WikiRecordRepository recordRepository;

    public WikiKafkaConsumer(WikiRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        logger.info("Message received: {}", message);
        WikiRecord wikiRecord = new WikiRecord();
        wikiRecord.setData(message);

        try {
            recordRepository.save(wikiRecord);
            logger.info("Record saved successfully.");
        } catch (Exception e) {
            logger.error("Error saving record: {}", e.getMessage(), e);
        }
    }

}
