package com.pe.walavo.challenge.business;

import com.pe.walavo.challenge.adapter.database.ChampionshipRepository;
import com.pe.walavo.challenge.adapter.database.ConfigurationRepository;
import com.pe.walavo.challenge.adapter.database.MatchRepository;
import com.pe.walavo.challenge.adapter.database.PlayerRepository;
import com.pe.walavo.challenge.business.championship.ChampionshipProcessor;
import com.pe.walavo.challenge.domain.Championship;
import com.pe.walavo.challenge.domain.Configuration;
import com.pe.walavo.challenge.dto.ChampionDTO;
import com.pe.walavo.challenge.dto.Request;
import com.pe.walavo.challenge.mapper.ConverterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ChampionshipService implements IChampionshipService {

    private final ChampionshipProcessor championshipProcessor;

    private final ChampionshipRepository championshipRepository;

    private final ConfigurationRepository configurationRepository;

    private final MatchRepository matchRepository;

    private final PlayerRepository playerRepository;

    @Override
    public Mono<ChampionDTO> process(Request request) {
        String winner = championshipProcessor.start(request);
        String type = request.getTypeChampionship().getParam();
        String name = request.getName();
        return Mono.zip(championshipRepository.findByConfigurationName(name),
                        configurationRepository.findByNameAndType(name, type),
                        playerRepository.findById(winner))
                .map(objects -> ConverterMapper.entityToApi(objects.getT2(), objects.getT1(), objects.getT3()));
    }
}
