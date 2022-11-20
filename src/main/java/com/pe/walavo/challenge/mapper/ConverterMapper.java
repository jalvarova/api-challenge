package com.pe.walavo.challenge.mapper;

import com.pe.walavo.challenge.domain.model.Championship;
import com.pe.walavo.challenge.domain.model.Configuration;
import com.pe.walavo.challenge.domain.model.Match;
import com.pe.walavo.challenge.domain.model.Player;
import com.pe.walavo.challenge.infraestructure.dto.ChampionDTO;
import com.pe.walavo.challenge.infraestructure.dto.MatchDTO;
import com.pe.walavo.challenge.infraestructure.dto.PlayerDTO;
import com.pe.walavo.challenge.infraestructure.dto.Request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pe.walavo.challenge.util.Utility.PARTY;
import static com.pe.walavo.challenge.util.Utility.identifier;

public final class ConverterMapper {

    public static ChampionDTO entityToApiChampion(Configuration configuration, Championship championship, Player player) {
        return ChampionDTO
                .builder()
                .championshipId(championship.getId())
                .amountMatch(configuration.getAmountMatch())
                .country(championship.getCountry())
                .date(championship.getEndDate())
                .watchTv(championship.getWatchTv())
                .type(configuration.getType())
                .firstAward(configuration.getFirstAward())
                .secondAward(configuration.getSecondAward())
                .name(configuration.getDescription())
                .playerWinner(PlayerDTO
                        .builder()
                        .name(player.getName())
                        .age(player.getAge())
                        .country(player.getCountry())
                        .skill(player.getSkill())
                        .document(player.getDocument())
                        .gender(player.getGender())
                        .build())
                .build();
    }

    public static Match apiToEntityMatch(
            String playerOne, String playerTwo,
            String playerWinner, String championship,
            String score, Integer phase, Integer party) {

        return Match
                .builder()
                .identifier(identifier(party.toString(), phase.toString(), championship))
                .phase(phase)
                .nameMatch(PARTY.concat(party.toString()))
                .numberMatch(party)
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
                .identifier(participants.getIdentifier())
                .configurationName(participants.getName())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(10))
                .build();

    }

    public static MatchDTO entityToApi(Player playerOne, Player playerTwo, Player playerWinner, Match match) {
        return MatchDTO
                .builder()
                .numberMatch(match.getNumberMatch())
                .nameMatch(match.getChampionship())
                .playerOne(entityToApi(playerOne))
                .playerTwo(entityToApi(playerTwo))
                .playerWinner(entityToApi(playerWinner))
                .score(match.getScore())
                .phase("Fase ".concat(match.getPhase().toString()))
                .startDate(match.getStartDate())
                .endDate(match.getEndDate())
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

    public static PlayerDTO entityToApi(Player playerDTO) {
        return PlayerDTO
                .builder()
                .document(playerDTO.getDocument())
                .age(playerDTO.getAge())
                .skill(playerDTO.getSkill())
                .name(playerDTO.getName())
                .country(playerDTO.getCountry())
                .gender(playerDTO.getGender())
                .build();

    }

    public static List<Player> apiToEntityPlayers(Request participants) {
        return participants
                .getPlayers()
                .stream()
                .map(ConverterMapper::apiToEntityPlayer)
                .toList();
    }


}
