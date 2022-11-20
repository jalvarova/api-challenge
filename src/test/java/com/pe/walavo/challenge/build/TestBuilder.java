package com.pe.walavo.challenge.build;

import com.pe.walavo.challenge.domain.model.Championship;
import com.pe.walavo.challenge.domain.model.Configuration;
import com.pe.walavo.challenge.domain.model.Match;
import com.pe.walavo.challenge.domain.model.Player;
import com.pe.walavo.challenge.infraestructure.dto.SearchChampionsDTO;

import java.time.LocalDateTime;
import java.util.Arrays;

public class TestBuilder {

    public static Player toFindPlayer(Player[] players, String document) {
        return Arrays.stream(players)
                .filter(p -> p.getDocument().equals(document))
                .findFirst()
                .get();
    }

    public static Player toPlayer() {
        return Player.
                builder()
                .document("47082603")
                .name("Carlos Aguinaga")
                .skill(85)
                .country("Peru")
                .gender('M')
                .age(41)
                .build();
    }

    public static Match toMatches() {
        return Match.
                builder()
                .championship("atp-world-tour-M-b94624a8-b975-48c1-b811-966c6c0ab471")
                .playerOne("47012903")
                .playerTwo("47081903")
                .numberMatch(1)
                .phase(1)
                .score("7-5")
                .build();
    }

    public static Championship toChampionship() {
        return Championship.
                builder()
                .identifier("atp-world-tour-M-31d6fac5-c031-4a38-aa1b-b1b09c84f62f")
                .type("M")
                .name("atp-world-tour")
                .country("Peru")
                .watchTv("Direct TV")
                .winner("47082603")
                .build();
    }

    public static Configuration toConfiguration() {
        return Configuration.
                builder()
                .name("atp-world-tour")
                .type("M")
                .description("ATP World Tour 2022")
                .firstAward(17000D)
                .secondAward(15000D)
                .amountMatch(8)
                .dateChampionship(LocalDateTime.now())
                .build();
    }

    public static SearchChampionsDTO toSearchChampionship() {
        return SearchChampionsDTO.
                builder()
                .name("atp-world-tour")
                .type("M")
                .limit(2)
                .page(0)
                .build();
    }
}
