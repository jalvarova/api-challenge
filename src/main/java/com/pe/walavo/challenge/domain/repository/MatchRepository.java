package com.pe.walavo.challenge.domain.repository;

import com.pe.walavo.challenge.domain.model.Match;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MatchRepository extends ReactiveCrudRepository<Match, String> {

    @Query("select * from championship.matches where championship = :championship order by phase")
    Flux<Match> findByChampionship(String championship);

}

