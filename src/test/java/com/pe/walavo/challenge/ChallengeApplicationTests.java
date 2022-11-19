package com.pe.walavo.challenge;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

//@SpringBootTest
@Slf4j
class ChallengeApplicationTests {

    @DisplayName("Test One")
    @Test
    public void test() {

        Flux.range(1, 4)
                .map(integer -> {
                    log.info("Integer " + integer);
                    log.info("Phase " + Math.round(integer/2f));
                    return integer;
                })
                .log("Iterable ")
                .subscribe();

        Flux.zip(Mono.just("a"), Mono.just("b"), Mono.just(12))
                .map(objects -> objects)
                .log()
                .subscribe();
        log.info("Test1");
    }
}
