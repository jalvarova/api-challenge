package com.pe.walavo.challenge.adapter.controller;

import com.pe.walavo.challenge.business.ChampionshipService;
import com.pe.walavo.challenge.dto.ChampionDTO;
import com.pe.walavo.challenge.dto.ChampionshipType;
import com.pe.walavo.challenge.dto.PlayerDTO;
import com.pe.walavo.challenge.dto.Request;
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
    @GetMapping(value = "/challenge/championships", produces = APPLICATION_JSON_VALUE)
    public Mono<?> getChampionship(@RequestParam("championship") @Size(min = 1, max = 100) String name,
                                   @RequestParam("config") String config,
                                   @RequestParam("type") ChampionshipType type) {
        return championshipService.getChampionship(name, config, type);
    }

    @Operation(description = "Championship processed to get the winner", operationId = "processWinnerChampionships", tags = {"championship"})
    @PostMapping(value = "/challenge/championships", produces = APPLICATION_JSON_VALUE)
    public Mono<ChampionDTO> processWinnerChampionships(@RequestBody Request request) {
        return championshipService.process(request);
    }
}