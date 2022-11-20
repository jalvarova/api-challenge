package com.pe.walavo.challenge.application.match;

import com.pe.walavo.challenge.application.player.FemaleCapacity;
import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Stream;

@Slf4j
@Component
public class FemaleMatch extends MatchProcessor {

    @Override
    public void play(PlayerDTO playerOne, PlayerDTO playerTwo) {
        log.info("Female Match initial");
        FemaleCapacity playerOneCapacity = new FemaleCapacity(playerOne);
        playerOneCapacity.measureCapacity();
        FemaleCapacity playerTwoCapacity = new FemaleCapacity(playerTwo);
        playerTwoCapacity.measureCapacity();

        setWinner(Stream.of(playerOne, playerTwo)
                .max(Comparator.comparing(PlayerDTO::getSkill))
                .get());

        float score1 = RandomUtils.nextFloat(5, 7);
        float score2 = RandomUtils.nextFloat(1, 4);

        setScore(String.join("-", Float.toString(score1), Float.toString(score2)));
    }
}

