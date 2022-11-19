package com.pe.walavo.challenge.business.match;

import com.pe.walavo.challenge.business.player.FemaleCapacity;
import com.pe.walavo.challenge.business.player.MaleCapacity;
import com.pe.walavo.challenge.dto.PlayerDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Stream;

@Component
@Slf4j
public class MaleMatch extends MatchProcessor {

    @Override
    public void play(PlayerDTO playerOne, PlayerDTO playerTwo) {
        log.info("Male Match initial");
        MaleCapacity playerOneCapacity = new MaleCapacity(playerOne);
        playerOneCapacity.measureCapacity();
        MaleCapacity playerTwoCapacity = new MaleCapacity(playerTwo);
        playerTwoCapacity.measureCapacity();

        setWinner(Stream.of(playerOne, playerTwo)
                .max(Comparator.comparing(PlayerDTO::getSkill))
                .get());

        float score1 = RandomUtils.nextFloat(5, 7);
        float score2 = RandomUtils.nextFloat(1, 4);


        setScore(String.join("-", String.valueOf(Math.round(score1)), String.valueOf(Math.round(score2))));

    }

}
