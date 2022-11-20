package com.pe.walavo.challenge.domain.repository;

import com.pe.walavo.challenge.domain.model.Configuration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ConfigurationRepository extends ReactiveCrudRepository<Configuration, Long> {
    Mono<Configuration> findByNameAndType(String id, String type);
}
