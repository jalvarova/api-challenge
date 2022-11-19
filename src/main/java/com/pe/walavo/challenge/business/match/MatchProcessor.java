package com.pe.walavo.challenge.business.match;

import com.pe.walavo.challenge.dto.PlayerDTO;
import lombok.Data;

@Data
public abstract class MatchProcessor {

    private PlayerDTO winner;

    private String score;

    public abstract void play(PlayerDTO playerOne, PlayerDTO playerTwo);

    public PlayerDTO winner() {
        return winner;
    }

    public String score() {
        return score;
    }
}
