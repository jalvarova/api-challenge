package com.pe.walavo.challenge.infraestructure.database;

import com.pe.walavo.challenge.domain.ChampionshipAccessDomain;
import com.pe.walavo.challenge.domain.model.Championship;
import com.pe.walavo.challenge.domain.model.Configuration;
import com.pe.walavo.challenge.domain.model.Match;
import com.pe.walavo.challenge.domain.model.Player;
import com.pe.walavo.challenge.domain.repository.ChampionshipRepository;
import com.pe.walavo.challenge.domain.repository.ConfigurationRepository;
import com.pe.walavo.challenge.domain.repository.MatchRepository;
import com.pe.walavo.challenge.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChampionshipDataAccess implements ChampionshipAccessDomain {


    private final ChampionshipRepository championshipRepository;

    private final ConfigurationRepository configurationRepository;

    private final MatchRepository matchRepository;

    private final PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    @Override
    public Mono<Configuration> findByNameAndType(String id, String type) {
        return configurationRepository.findByNameAndType(id, type);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Match> findByChampionship(String championship) {
        return matchRepository.findByChampionship(championship);
    }

    @Transactional
    @Override
    public Mono<Match> save(Match match) {
        return matchRepository.save(match);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Player> findAllPlayers() {
        return playerRepository.findAllPlayers();
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Player> findPlayerById(String document) {
        return playerRepository.findById(document);
    }

    @Transactional
    @Override
    public Flux<Player> saveAllPlayers(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    @Transactional
    @Override
    public Mono<Championship> save(Championship championship) {
        return championshipRepository.save(championship);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<Championship> findAllChampionshipById(String identifier) {
        return championshipRepository.findByConfigurationName(identifier);
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Championship> findChampionshipById(String identifier) {
        return championshipRepository.findById(identifier);
    }

    @Transactional
    @Override
    public Flux<?> saveAllPlayersValidate(List<Player> players) {
        return Flux.fromIterable(players)
                .flatMap(player -> playerRepository.findById(player.getDocument()))
                .switchIfEmpty(playerRepository.saveAll(players));

    }
}
