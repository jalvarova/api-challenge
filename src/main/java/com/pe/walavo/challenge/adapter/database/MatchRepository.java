package com.pe.walavo.challenge.adapter.database;

import com.pe.walavo.challenge.domain.Match;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MatchRepository extends ReactiveCrudRepository<Match, String> {

    Mono<Match> findByChampionshipAndNumberMatch(String championship, Integer number);

}

