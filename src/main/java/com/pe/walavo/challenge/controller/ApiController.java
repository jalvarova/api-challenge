package com.pe.walavo.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Size;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Validated
public class ApiController {

    @GetMapping(
            value = "/challenge",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<?> get(@RequestParam("name") @Size(min = 1,max = 20) String name) {
        return Mono.just("Hello World ".concat(name));
    }
}
