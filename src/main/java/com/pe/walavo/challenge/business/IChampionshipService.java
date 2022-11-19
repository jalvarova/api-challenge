package com.pe.walavo.challenge.business;

import com.pe.walavo.challenge.dto.ChampionDTO;
import com.pe.walavo.challenge.dto.ChampionshipType;
import com.pe.walavo.challenge.dto.PlayerDTO;
import com.pe.walavo.challenge.dto.Request;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IChampionshipService {
    Mono<ChampionDTO> process(Request request) throws InterruptedException;

    Mono<?> getChampionship(String championship, String config, ChampionshipType typeChampionship);

    Mono<?> matches(String championship);

    Flux<PlayerDTO> players();
}
