package com.pe.walavo.challenge.business.championship;

import com.pe.walavo.challenge.adapter.database.ChampionshipRepository;
import com.pe.walavo.challenge.adapter.database.ConfigurationRepository;
import com.pe.walavo.challenge.adapter.database.MatchRepository;
import com.pe.walavo.challenge.adapter.database.PlayerRepository;
import com.pe.walavo.challenge.business.match.MaleMatch;
import com.pe.walavo.challenge.domain.Championship;
import com.pe.walavo.challenge.domain.Configuration;
import com.pe.walavo.challenge.domain.Player;
import com.pe.walavo.challenge.dto.PlayerDTO;
import com.pe.walavo.challenge.dto.Request;
import com.pe.walavo.challenge.dto.TYPE_CHAMPIONSHIP;
import com.pe.walavo.challenge.mapper.ConverterMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.pe.walavo.challenge.mapper.ConverterMapper.*;
import static com.pe.walavo.challenge.util.Utility.countPhase;
import static reactor.core.publisher.Flux.zip;
import static reactor.core.publisher.Flux.range;

import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class ChampionshipTennis implements ChampionshipProcessor {

    private final MaleMatch maleMatch;

    private final ConfigurationRepository configurationRepository;

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    private final ChampionshipRepository championshipRepository;

    @Override
    public void start(Request participants) {
        TYPE_CHAMPIONSHIP type = participants.getTypeChampionship();

        List<Player> players = participants
                .getPlayers()
                .stream()
                .map(ConverterMapper::apiToEntityPlayer)
                .toList();


        saveAll(players).subscribe();
        Championship championship = apiToEntityChampionship(participants);
        championshipRepository.save(championship)
                .flatMap(configuration -> configurationRepository.findByNameAndType(participants.getName(), type.getParam()))
                .flux()
                .map(Configuration::getAmountMatch)
                .flatMap(integer -> playTourney(integer, participants, championship))
                .subscribe();
    }


    @Transactional
    private Flux<?> saveAll(List<Player> players) {
        return playerRepository.saveAll(players);
    }

    private Flux<?> playTourney(Integer countPlayers, Request participants, Championship championship) {
        List<PlayerDTO> arrayListWinners = new ArrayList<>();
        List<PlayerDTO> playersList = participants.getPlayers();

        return range(1, countPhase(countPlayers) - 1)
                .map(phase -> {
                    arrayListWinners.clear();
                    setPhase(participants.getPlayers())
                            .stream()
                            .collect(Collectors.groupingBy(PlayerDTO::getPhase))
                            .forEach((key, value) -> {
                                PlayerDTO playerDTOOne = value.get(0);
                                PlayerDTO playerDTOTwo = value.get(1);
                                maleMatch.play(playerDTOOne, playerDTOTwo);
                                PlayerDTO playerDtoWinner = maleMatch.winner();
                                arrayListWinners.add(playerDtoWinner);
                                String score = maleMatch.score();
                                matchRepository.save(apiToEntityMatch(
                                                playerDTOOne.getDocument(), playerDTOTwo.getDocument(),
                                                playerDtoWinner.getDocument(), championship.getIdentifier(),
                                                score, phase, key))
                                        .subscribe(match -> log.info("Match " + match.toString()));
                            });
                    playersList.clear();
                    playersList.addAll(arrayListWinners);
                    return false;
                });

    }
}
