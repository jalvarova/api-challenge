package com.pe.walavo.challenge.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pe.walavo.challenge.domain.Match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonPropertyOrder({"name", "player", "amountMatch", "awards", "date"})
public class ChampionDTO {


    private String name;

    private String type;

    private Long firstAward;

    private Long secondAward;

    private Short amountMatch;

    private String country;

    private String watchTv;

    private LocalDateTime date;

    private PlayerDTO playerWinner;
}