package com.pe.walavo.challenge.adapter.database;

import com.pe.walavo.challenge.domain.Configuration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ConfigurationRepository extends ReactiveCrudRepository<Configuration, Long> {
    Mono<Configuration> findByNameAndType(String id, String type);
}
