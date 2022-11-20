package com.pe.walavo.challenge.domain.repository;

import com.pe.walavo.challenge.domain.model.Player;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface PlayerRepository extends ReactiveCrudRepository<Player, String> {

    @Query("select * from championship.players")
    Flux<Player> findAllPlayers();
}

