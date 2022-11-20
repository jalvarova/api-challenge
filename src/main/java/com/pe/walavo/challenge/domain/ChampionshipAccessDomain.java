package com.pe.walavo.challenge.domain;

import com.pe.walavo.challenge.domain.model.Championship;
import com.pe.walavo.challenge.domain.model.Configuration;
import com.pe.walavo.challenge.domain.model.Match;
import com.pe.walavo.challenge.domain.model.Player;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public interface ChampionshipAccessDomain {

    Mono<Configuration> findByNameAndType(String id, String type);

    Flux<Match> findByChampionship(String championship);

    Mono<Match> save(Match match);

    Flux<Player> findAllPlayers();

    Mono<Player> findPlayerById(String document);

    Flux<Player> saveAllPlayers(List<Player> players);

    Mono<Championship> save(Championship championship);

    Flux<Championship> findAllChampionshipByName(String identifier);

    Mono<Championship> findChampionshipById(String identifier);

    Flux<Championship> findChampionshipSearch(String identifier, String type,
                                              LocalDateTime from, LocalDateTime to,
                                              Pageable pageable);

    Flux<?> saveAllPlayersValidate(List<Player> players);
}
