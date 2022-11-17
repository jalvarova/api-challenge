package com.pe.walavo.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Validated
public class ApiController {

    @Operation(
            description = "Retrieve attachments to a existing Ticket",
            operationId = "getOperation",
            tags = {"champions"}
    )
    @GetMapping(
            value = "/challenge",
            produces = APPLICATION_JSON_VALUE
    )
    public Mono<?> getOperation(@RequestParam("name") @Size(min = 1, max = 20) @Pattern(regexp = "^[a-zA-Z]*$") String name) {
        return Mono.just("Hello World ".concat(name));
    }
}
