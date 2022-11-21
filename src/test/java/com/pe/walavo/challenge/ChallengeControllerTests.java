package com.pe.walavo.challenge;


import com.pe.walavo.challenge.application.ChampionshipService;
import com.pe.walavo.challenge.infraestructure.controller.ApiController;
import com.pe.walavo.challenge.infraestructure.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;

import static com.pe.walavo.challenge.build.TestBuilder.toSearchChampionship;
import static com.pe.walavo.challenge.util.ConvertUtil.fileToObject;
import static com.pe.walavo.challenge.util.ConvertUtil.jsonToString;
import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebFluxTest(ApiController.class)
public class ChallengeControllerTests {

    private final String TEST_EMAIL = "test@gmail.com";

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ChampionshipService championshipService;

    private static PlayerDTO[] players;
    private static ChampionDTO championDTO;
    private static MatchDTO[] matchesDtos;

    private static Request request;

    @BeforeEach
    void initial() throws IOException {
        players = fileToObject("json/players.json", PlayerDTO[].class);
        championDTO = fileToObject("json/get-championship.json", ChampionDTO.class);
        matchesDtos = fileToObject("json/matches-dto.json", MatchDTO[].class);
        request = fileToObject("json/request.json", Request.class);

        log.info("initial Test");
    }

    @Test
    public void testApiPlayer() {

        given(championshipService.players()).willReturn(Flux.just(players));

        webClient
                .get()
                .uri("/api/v1/challenge/championships/players")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .json(jsonToString(players));
    }

    @Test
    public void testApiGetChampionship() {

        String identifier = "atp-world-tour-M-31d6fac5-c031-4a38-aa1b-b1b09c84f62f";

        given(championshipService.getChampionship(identifier))
                .willReturn(Mono.just(championDTO));

        webClient
                .get()
                .uri("/api/v1/challenge/championships/" + identifier)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .json(jsonToString(championDTO));
    }

    @Test
    public void testApiGetMatchesByChampionship() {

        String identifier = "atp-world-tour-M-31d6fac5-c031-4a38-aa1b-b1b09c84f62f";

        given(championshipService.matches(identifier)).willReturn(Flux.just(matchesDtos));

        webClient
                .get()
                .uri("/api/v1/challenge/championships/" + identifier + "/matches")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testApiGetChampionshipSearch() {

        SearchChampionsDTO searchChampionsDTO = toSearchChampionship();

        given(championshipService.searchParam(searchChampionsDTO)).willReturn(Flux.just(championDTO));

        webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/challenge/championships/search")
                        .queryParam("name", searchChampionsDTO.getName())
                        .queryParam("type", searchChampionsDTO.getType())
                        .queryParam("page", searchChampionsDTO.getPage())
                        .queryParam("limit", searchChampionsDTO.getLimit())
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .json(jsonToString(Collections.singletonList(championDTO)));
    }

    @Test
    public void testApiMatchesByChampionships() {

        String identifier = "atp-world-tour";

        given(championshipService.matches(identifier)).willReturn(Flux.just(matchesDtos));

        webClient
                .get()
                .uri("/api/v1/challenge/championships/" + identifier + "/matches")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody();
    }

    @Test
    public void testApiProcessWinnerChampionships() {

        given(championshipService.process(request)).willReturn(Mono.just(championDTO));

        webClient
                .post()
                .uri("/api/v1/challenge/championships")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonToString(request))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .json(jsonToString(championDTO));
    }
}