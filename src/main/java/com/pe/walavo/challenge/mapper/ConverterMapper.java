package com.pe.walavo.challenge.mapper;

import com.pe.walavo.challenge.domain.Championship;
import com.pe.walavo.challenge.domain.Match;
import com.pe.walavo.challenge.domain.Player;
import com.pe.walavo.challenge.dto.PlayerDTO;
import com.pe.walavo.challenge.dto.Request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pe.walavo.challenge.util.Utility.identifier;

public final class ConverterMapper {

    public static Match apiToEntityMatch(
            String playerOne, String playerTwo,
            String playerWinner, String championship,
            String score, Integer phase, Integer party) {

        return Match
                .builder()
                .identifier(party.toString().concat("-").concat(phase.toString()).concat("-").concat(championship))
                .phase(phase)
                .nameMatch("Party ".concat(party.toString()))
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .playerWinner(playerWinner)
                .championship(championship)
                .score(score)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(60L))
                .build();

    }

    public static Championship apiToEntityChampionship(Request participants) {
        return Championship
                .builder()
                .country(participants.getCountry())
                .watchTv(participants.getWatchTv())
                .identifier(identifier(participants))
                .configurationName(participants.getName())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(10))
                .build();

    }

    public static List<PlayerDTO> setPhase(List<PlayerDTO> playersList) {
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        int count = playersList.size();
        for (PlayerDTO playerDTO : playersList) {
            playerDTO.setPhase(Math.round(count / 2f));
            count--;
            playerDTOList.add(playerDTO);
        }
        return playerDTOList;
    }


    public static Player apiToEntityPlayer(PlayerDTO playerDTO) {
        return Player
                .builder()
                .document(playerDTO.getDocument())
                .age(playerDTO.getAge())
                .skill(playerDTO.getSkill())
                .name(playerDTO.getName())
                .country(playerDTO.getCountry())
                .gender(playerDTO.getGender())
                .build();

    }

}
