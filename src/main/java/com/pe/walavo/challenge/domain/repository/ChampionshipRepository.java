package com.pe.walavo.challenge.domain.repository;

import com.pe.walavo.challenge.domain.model.Championship;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChampionshipRepository extends ReactiveCrudRepository<Championship, String> {
    Flux<Championship> findByConfigurationName(String configurationName);

}

