package com.pe.walavo.challenge.infraestructure.controller;

import com.pe.walavo.challenge.application.ChampionshipService;
import com.pe.walavo.challenge.infraestructure.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Size;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class ApiController {

    private final ChampionshipService championshipService;

    @Operation(description = "Match of a championship players by participation championships", operationId = "getByPlayers", tags = {"championship"})
    @GetMapping(value = "/challenge/championships/players", produces = APPLICATION_JSON_VALUE)
    public Flux<PlayerDTO> getByPlayers() {
        return championshipService.players();
    }

    @Operation(description = "Match of a championship players championships", operationId = "getMatchByPlayer", tags = {"championship"})
    @GetMapping(value = "/challenge/championships/{championshipName}/matches", produces = APPLICATION_JSON_VALUE)
    public Mono<?> getMatchesByChampionship(@PathVariable("championshipName") @Size(min = 1, max = 100) String championshipName) {
        return championshipService.matches(championshipName);
    }

    @Operation(description = "Get championship by name", operationId = "getChampionship", tags = {"championship"})
    @GetMapping(value = "/challenge/championships/search", produces = APPLICATION_JSON_VALUE)
    public Flux<ChampionDTO> getChampionshipSearch(SearchChampionsDTO searchChampionsDTO) {
        return championshipService.searchParam(searchChampionsDTO);
    }

    @Operation(description = "Get championship by name", operationId = "getChampionship", tags = {"championship"})
    @GetMapping(value = "/challenge/championships/{championship}", produces = APPLICATION_JSON_VALUE)
    public Mono<?> getChampionship(@PathVariable("championship") @Size(min = 1, max = 100) String name) {
        return championshipService.getChampionship(name);
    }

    @Operation(description = "Championship processed to get the winner", operationId = "processWinnerChampionships", tags = {"championship"})
    @PostMapping(value = "/challenge/championships", produces = APPLICATION_JSON_VALUE)
    public Mono<ChampionDTO> processWinnerChampionships(@RequestBody Request request) {
        return championshipService.process(request);
    }
}
