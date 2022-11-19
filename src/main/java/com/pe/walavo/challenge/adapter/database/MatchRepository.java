package com.pe.walavo.challenge.adapter.database;

import com.pe.walavo.challenge.domain.Match;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MatchRepository extends ReactiveCrudRepository<Match, String> {

}

