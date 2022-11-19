package com.pe.walavo.challenge.business;

import com.pe.walavo.challenge.business.championship.ChampionshipProcessor;
import com.pe.walavo.challenge.dto.ChampionDTO;
import com.pe.walavo.challenge.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ChampionshipService implements IChampionshipService{

    private final ChampionshipProcessor championshipProcessor;

    @Override
    public Mono<ChampionDTO> process(Request request) {
        championshipProcessor.start(request);
        return null;
    }
}
