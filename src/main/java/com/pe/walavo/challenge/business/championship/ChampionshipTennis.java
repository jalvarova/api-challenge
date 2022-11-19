package com.pe.walavo.challenge.business.championship;

import com.pe.walavo.challenge.adapter.database.ChampionshipRepository;
import com.pe.walavo.challenge.adapter.database.ConfigurationRepository;
import com.pe.walavo.challenge.adapter.database.MatchRepository;
import com.pe.walavo.challenge.adapter.database.PlayerRepository;
import com.pe.walavo.challenge.business.match.MaleMatch;
import com.pe.walavo.challenge.domain.Championship;
import com.pe.walavo.challenge.domain.Player;
import com.pe.walavo.challenge.dto.ChampionshipType;
import com.pe.walavo.challenge.dto.PlayerDTO;
import com.pe.walavo.challenge.dto.Request;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pe.walavo.challenge.mapper.ConverterMapper.*;
import static com.pe.walavo.challenge.util.Utility.countPhase;
import static reactor.core.publisher.Flux.range;

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
    public String start(Request participants) {
        ChampionshipType type = participants.getTypeChampionship();
        List<Player> players = apiToEntityPlayers(participants);
        saveAll(players).subscribe(o -> log.info("Player " + o.toString()));
        Championship championship = apiToEntityChampionship(participants);
        return playTourney(players.size(), participants, championship);
    }

    @Transactional
    private Flux<?> saveAll(List<Player> players) {
        return Flux.fromIterable(players)
                .flatMap(player -> playerRepository.findById(player.getDocument()))
                .switchIfEmpty(playerRepository.saveAll(players));
    }

    @SneakyThrows
    private String playTourney(Integer countPlayers, Request participants, Championship championship)  {
        List<PlayerDTO> arrayListWinners = new ArrayList<>();
        List<PlayerDTO> playersList = participants.getPlayers();
        range(1, countPhase(countPlayers) - 1)
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
                                championship.setWinner(playerDtoWinner.getDocument());
                                arrayListWinners.add(playerDtoWinner);
                                String score = maleMatch.score();
                                matchRepository.save(apiToEntityMatch(
                                        playerDTOOne.getDocument(), playerDTOTwo.getDocument(),
                                        playerDtoWinner.getDocument(), championship.getIdentifier(),
                                        score, phase, key)).subscribe(match -> log.info("Match " + match.toString()));
                            });
                    playersList.clear();
                    playersList.addAll(arrayListWinners);
                    return true;
                }).subscribe(o -> log.info("Validate " + o.toString()));

        championshipRepository.save(championship).subscribe(o -> log.info("Championship " + o.toString()));

        Thread.sleep(150);
        return championship.getWinner();
    }
}
