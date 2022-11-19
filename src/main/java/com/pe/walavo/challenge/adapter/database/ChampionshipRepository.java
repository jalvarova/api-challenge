package com.pe.walavo.challenge.adapter.database;

import com.pe.walavo.challenge.domain.Championship;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChampionshipRepository extends ReactiveCrudRepository<Championship, String> {

}

