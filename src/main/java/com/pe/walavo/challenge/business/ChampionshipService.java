package com.pe.walavo.challenge.business;

import com.pe.walavo.challenge.adapter.database.ChampionshipRepository;
import com.pe.walavo.challenge.adapter.database.ConfigurationRepository;
import com.pe.walavo.challenge.adapter.database.MatchRepository;
import com.pe.walavo.challenge.adapter.database.PlayerRepository;
import com.pe.walavo.challenge.business.championship.ChampionshipProcessor;
import com.pe.walavo.challenge.dto.*;
import com.pe.walavo.challenge.mapper.ConverterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.pe.walavo.challenge.mapper.ConverterMapper.entityToApi;
import static com.pe.walavo.challenge.mapper.ConverterMapper.entityToApiChampion;
import static com.pe.walavo.challenge.util.Utility.identifier;

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
        String identifier = identifier(request);
        request.setIdentifier(identifier);
        String winner = championshipProcessor.start(request);
        String type = request.getTypeChampionship().getParam();
        String name = request.getName();

        return Mono.zip(championshipRepository.findById(identifier),
                        configurationRepository.findByNameAndType(name, type),
                        playerRepository.findById(winner))
                .map(objects -> entityToApiChampion(objects.getT2(), objects.getT1(), objects.getT3()));
    }

    @Override
    public Mono<ChampionDTO> getChampionship(String championship, String config, ChampionshipType typeChampionship) {
        return Mono.zip(championshipRepository.findById(championship),
                        configurationRepository.findByNameAndType(config, typeChampionship.getParam()))
                .flatMap(o -> playerRepository.findById(o.getT1().getWinner())
                .map(player ->  ConverterMapper.entityToApiChampion(o.getT2(), o.getT1(),player)));

    }

    @Override
    public Mono<?> matches(String championship) {
        return matchRepository
                .findByChampionship(championship)
                .flatMap(match -> Flux.zip(
                                playerRepository.findById(match.getPlayerOne()),
                                playerRepository.findById(match.getPlayerTwo()),
                                playerRepository.findById(match.getPlayerWinner()))
                        .map(o -> entityToApi(o.getT1(), o.getT2(), o.getT3(), match)))
                .sort(Comparator.comparing(MatchDTO::getPhase))
                .collectMultimap(MatchDTO::getPhase);
    }

    @Override
    public Flux<PlayerDTO> players() {
        return playerRepository.findAllPlayers().map(ConverterMapper::entityToApi);
    }
}
