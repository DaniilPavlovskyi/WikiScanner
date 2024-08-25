package com.daniil.wikikafkaconsumer.repository;

import com.daniil.wikikafkaconsumer.entity.WikiRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiRecordRepository extends JpaRepository<WikiRecord, Long> {
}
