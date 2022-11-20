package com.pe.walavo.challenge;

import com.pe.walavo.challenge.application.ChampionshipService;
import com.pe.walavo.challenge.application.championship.ChampionshipProcessor;

import static com.pe.walavo.challenge.build.TestBuilder.*;

import com.pe.walavo.challenge.domain.ChampionshipAccessDomain;
import com.pe.walavo.challenge.domain.model.Championship;
import com.pe.walavo.challenge.domain.model.Configuration;
import com.pe.walavo.challenge.domain.model.Match;
import com.pe.walavo.challenge.domain.model.Player;
import com.pe.walavo.challenge.infraestructure.dto.ChampionDTO;
import com.pe.walavo.challenge.infraestructure.dto.MatchDTO;
import com.pe.walavo.challenge.infraestructure.dto.SearchChampionsDTO;
import com.pe.walavo.challenge.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@ExtendWith(SpringExtension.class)
class ChallengeApplicationTests {

    @InjectMocks
    private ChampionshipService championshipService;

    @Mock
    private ChampionshipProcessor championshipProcessor;

    @Mock
    private ChampionshipAccessDomain championshipAccessDomain;

    private static Player[] players;
    private static ChampionDTO championDTO;

    private static MatchDTO[] matchesDtos;
    private static Match[] matches;

    @BeforeEach
    void initial() throws IOException {
        players = ConvertUtil.fileToObject("json/players.json", Player[].class);
        championDTO = ConvertUtil.fileToObject("json/get-championship.json", ChampionDTO.class);
        matchesDtos = ConvertUtil.fileToObject("json/matches-dto.json", MatchDTO[].class);
        matches = ConvertUtil.fileToObject("json/matches.json", Match[].class);

        log.info("initial Test");
    }

    @Test
    @DisplayName("Test count amount players insert")
    public void testFindAllPlayers() {

        Flux<Player> listPlayers = Flux.fromIterable(Arrays.asList(players));

        when(championshipAccessDomain.findAllPlayers()).thenReturn(listPlayers);

        championshipService
                .players()
                .log()
                .as(StepVerifier::create)
                .expectNextCount(8)
                .verifyComplete();

    }


    @Test
    @DisplayName("Test get championship")
    public void testGetChampionship() {

        String name = "atp-world-tour";
        String identifier = "atp-world-tour-M-31d6fac5-c031-4a38-aa1b-b1b09c84f62f";
        String type = "M";
        String document = "47082603";
        Player player = toPlayer();
        Championship championship = toChampionship();
        Configuration configuration = toConfiguration();


        when(championshipAccessDomain.findPlayerById(document)).thenReturn(Mono.just(player));

        when(championshipAccessDomain.findByNameAndType(name, type))
                .thenReturn(Mono.just(configuration));


        when(championshipAccessDomain.findChampionshipById(identifier))
                .thenReturn(Mono.just(championship));

        championshipService
                .getChampionship(identifier)
                .log()
                .as(StepVerifier::create)
                .expectNext(championDTO)
                .verifyComplete();

    }

    @Test
    @DisplayName("Test get championship")
    public void testGetAllChampionship() {

        String document = "47082603";
        Player player = toPlayer();
        Championship championship = toChampionship();
        Configuration configuration = toConfiguration();
        SearchChampionsDTO searchParam = toSearchChampionship();

        when(championshipAccessDomain.findPlayerById(document)).thenReturn(Mono.just(player));

        when(championshipAccessDomain.findByNameAndType(searchParam.getName(), searchParam.getType()))
                .thenReturn(Mono.just(configuration));


        when(championshipAccessDomain.findChampionshipSearch(
                searchParam.getName(), searchParam.getType(), null, null,
                PageRequest.of(searchParam.getPage(), searchParam.getLimit())))
                .thenReturn(Flux.just(championship));

        championshipService
                .searchParam(searchParam)
                .log("All Championship")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

    }


    @Test
    @DisplayName("Test get Match by Championship")
    public void testMatchesByChampionship() {

        String identifier = "atp-world-tour-M-b94624a8-b975-48c1-b811-966c6c0ab471";

        when(championshipAccessDomain.findByChampionship(identifier))
                .thenReturn(Flux.just(matches));

        Arrays.stream(matches).forEach(match -> {

            when(championshipAccessDomain.findPlayerById(match.getPlayerOne()))
                    .thenReturn(Mono.just(toFindPlayer(players, match.getPlayerOne())));
            when(championshipAccessDomain.findPlayerById(match.getPlayerTwo()))
                    .thenReturn(Mono.just(toFindPlayer(players, match.getPlayerTwo())));
            when(championshipAccessDomain.findPlayerById(match.getPlayerWinner()))
                    .thenReturn(Mono.just(toFindPlayer(players, match.getPlayerWinner())));

        });


        championshipService
                .matches(identifier)
                .log("All Championship")
                .as(StepVerifier::create)
                .expectNextCount(7)
                .verifyComplete();

    }

    @AfterEach
    void end() {
        log.info("End Test");
    }

}
