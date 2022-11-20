package com.pe.walavo.challenge.application.match;

import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
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
