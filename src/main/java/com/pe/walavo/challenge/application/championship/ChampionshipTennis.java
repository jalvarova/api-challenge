package com.pe.walavo.challenge.application.championship;

import com.pe.walavo.challenge.application.aparter.MatchProcessorAdapter;
import com.pe.walavo.challenge.application.match.MatchProcessor;
import com.pe.walavo.challenge.domain.ChampionshipAccessDomain;
import com.pe.walavo.challenge.domain.model.Championship;
import com.pe.walavo.challenge.domain.model.Player;
import com.pe.walavo.challenge.infraestructure.dto.ChampionshipType;
import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
import com.pe.walavo.challenge.infraestructure.dto.Request;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    private final MatchProcessorAdapter processorAdapter;
    private final ChampionshipAccessDomain championshipAccessDomain;

    @Override
    public String start(Request participants) {
        List<Player> players = apiToEntityPlayers(participants);
        championshipAccessDomain.saveAllPlayersValidate(players).subscribe(o -> log.info("Player " + o.toString()));
        Championship championship = apiToEntityChampionship(participants);
        return playTourney(players.size(), participants, championship);
    }


    @SneakyThrows
    private String playTourney(Integer countPlayers, Request participants, Championship championship) {
        List<PlayerDTO> arrayListWinners = new ArrayList<>();
        List<PlayerDTO> playersList = participants.getPlayers();
        ChampionshipType type = participants.getTypeChampionship();
        MatchProcessor adapter = processorAdapter.strategy(type.getParam());
        range(1, countPhase(countPlayers) - 1)
                .map(phase -> {
                    arrayListWinners.clear();
                    setPhase(participants.getPlayers())
                            .stream()
                            .collect(Collectors.groupingBy(PlayerDTO::getPhase))
                            .forEach((key, value) -> {
                                PlayerDTO playerDTOOne = value.get(0);
                                PlayerDTO playerDTOTwo = value.get(1);
                                adapter.play(playerDTOOne, playerDTOTwo);
                                PlayerDTO playerDtoWinner = adapter.winner();
                                championship.setWinner(playerDtoWinner.getDocument());
                                arrayListWinners.add(playerDtoWinner);
                                String score = adapter.score();
                                championshipAccessDomain.save(apiToEntityMatch(
                                        playerDTOOne.getDocument(), playerDTOTwo.getDocument(),
                                        playerDtoWinner.getDocument(), championship.getIdentifier(),
                                        score, phase, key)).subscribe(match -> log.info("Match " + match.toString()));
                            });
                    playersList.clear();
                    playersList.addAll(arrayListWinners);
                    return true;
                }).subscribe(o -> log.info("Validate " + o.toString()));

        championshipAccessDomain.save(championship).subscribe(o -> log.info("Championship " + o.toString()));

        Thread.sleep(150);
        return championship.getWinner();
    }
}
