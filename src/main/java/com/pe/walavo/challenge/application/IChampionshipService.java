package com.pe.walavo.challenge.application;

import com.pe.walavo.challenge.infraestructure.dto.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IChampionshipService {
    Mono<ChampionDTO> process(Request request) throws InterruptedException;

    Mono<?> getChampionship(String name);

    Flux<ChampionDTO> searchParam(SearchChampionsDTO searchParam);

    Flux<MatchDTO> matches(String championship);

    Flux<PlayerDTO> players();
}
