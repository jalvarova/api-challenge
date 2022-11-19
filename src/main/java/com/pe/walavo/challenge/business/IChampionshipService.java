package com.pe.walavo.challenge.business;

import com.pe.walavo.challenge.dto.ChampionDTO;
import com.pe.walavo.challenge.dto.Request;
import reactor.core.publisher.Mono;

public interface IChampionshipService {
    Mono<ChampionDTO> process(Request request);

}
