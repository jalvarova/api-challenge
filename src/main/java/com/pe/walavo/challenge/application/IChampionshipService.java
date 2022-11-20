package com.pe.walavo.challenge.application;

import com.pe.walavo.challenge.infraestructure.dto.ChampionDTO;
import com.pe.walavo.challenge.infraestructure.dto.ChampionshipType;
import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
import com.pe.walavo.challenge.infraestructure.dto.Request;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IChampionshipService {
    Mono<ChampionDTO> process(Request request) throws InterruptedException;

    Mono<?> getChampionship(String championship, String config, ChampionshipType typeChampionship);

    Mono<?> matches(String championship);

    Flux<PlayerDTO> players();
}
