package com.pe.walavo.challenge.adapter.database;

import com.pe.walavo.challenge.domain.Match;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MatchRepository extends ReactiveCrudRepository<Match, String> {

    @Query("select * from championship.matches where championship = :championship order by phase")
    Flux<Match> findByChampionship(String championship);

}

