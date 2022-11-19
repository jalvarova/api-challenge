package com.pe.walavo.challenge.util;

import com.pe.walavo.challenge.dto.Request;

import java.util.UUID;

public final class Utility {

    public static final String DELIMITER = "-";
    public static final String PARTY = "Party ";

    public static String identifier(Request participants) {
        return String.join(DELIMITER,
                participants.getName(),
                participants.getTypeChampionship().getParam(),
                UUID.randomUUID().toString());
    }

    public static String identifier(CharSequence... charSequence) {
        return String.join(DELIMITER, charSequence);
    }

    public static int countPhase(Integer countPlayers) {
        return countPlayers / 2;
    }

}
