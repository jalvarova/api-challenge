package com.pe.walavo.challenge.domain.repository;

import com.pe.walavo.challenge.domain.model.Championship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface ChampionshipRepository extends ReactiveCrudRepository<Championship, String> {
    Flux<Championship> findByName(String configurationName);

    /*    @Query("select * from championship.championships " +
                "where configuration_name= :id " +
                "AND " +
                "configuration_type= :type")*/
//            "or " +
//            "start_date BETWEEN :from AND :to")
    Flux<Championship> findAllByNameOrType(String id, String type, Pageable page);

    Flux<Championship> findAllBy(Pageable page);
}

