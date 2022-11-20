package com.pe.walavo.challenge.application;

import com.pe.walavo.challenge.application.championship.ChampionshipProcessor;
import com.pe.walavo.challenge.domain.ChampionshipAccessDomain;
import com.pe.walavo.challenge.infraestructure.dto.*;
import com.pe.walavo.challenge.mapper.ConverterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.pe.walavo.challenge.mapper.ConverterMapper.entityToApi;
import static com.pe.walavo.challenge.mapper.ConverterMapper.entityToApiChampion;
import static com.pe.walavo.challenge.util.Utility.identifier;

@RequiredArgsConstructor
@Service
public class ChampionshipService implements IChampionshipService {

    private final ChampionshipProcessor championshipProcessor;

    private final ChampionshipAccessDomain championshipAccessDomain;

    @Override
    public Mono<ChampionDTO> process(Request request) {
        String identifier = identifier(request);
        request.setIdentifier(identifier);
        String winner = championshipProcessor.start(request);
        String type = request.getTypeChampionship().getParam();
        String name = request.getName();

        return Mono.zip(championshipAccessDomain.findChampionshipById(identifier),
                        championshipAccessDomain.findByNameAndType(name, type),
                        championshipAccessDomain.findPlayerById(winner))
                .map(objects -> entityToApiChampion(objects.getT2(), objects.getT1(), objects.getT3()));
    }

    @Override
    public Mono<ChampionDTO> getChampionship(String name) {
        return championshipAccessDomain
                .findChampionshipById(name)
                .flatMap(championship -> Mono.zip(
                                championshipAccessDomain.findByNameAndType(
                                        championship.getName(),
                                        championship.getType()),
                                championshipAccessDomain.findPlayerById(championship.getWinner()))
                        .map(o -> ConverterMapper.entityToApiChampion(o.getT1(), championship, o.getT2())));
    }

    public Flux<ChampionDTO> searchParam(SearchChampionsDTO searchParam) {
        Pageable pageable = PageRequest.of(searchParam.getPage(), searchParam.getLimit());
        return championshipAccessDomain.findChampionshipSearch(searchParam.getName(), searchParam.getType(),
                        searchParam.getFrom(), searchParam.getTo(), pageable)
                .flatMap(championship -> Flux.zip(
                                championshipAccessDomain.findByNameAndType(
                                        championship.getName(),
                                        championship.getType()),
                                championshipAccessDomain.findPlayerById(championship.getWinner()))
                        .map(o -> ConverterMapper.entityToApiChampion(o.getT1(), championship, o.getT2())));

    }

    @Override
    public Flux<MatchDTO> matches(String championship) {
        return championshipAccessDomain
                .findByChampionship(championship)
                .flatMap(match -> Flux.zip(
                                championshipAccessDomain.findPlayerById(match.getPlayerOne()),
                                championshipAccessDomain.findPlayerById(match.getPlayerTwo()),
                                championshipAccessDomain.findPlayerById(match.getPlayerWinner()))
                        .map(o -> entityToApi(o.getT1(), o.getT2(), o.getT3(), match)));

    }

    @Override
    public Flux<PlayerDTO> players() {
        return championshipAccessDomain.findAllPlayers().map(ConverterMapper::entityToApi);
    }
}
