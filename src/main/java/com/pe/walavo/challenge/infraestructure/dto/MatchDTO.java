package com.pe.walavo.challenge.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonPropertyOrder({"nameMatch", "playerOne", "playerTwo", "playerWinner", "score", "startDate", "endDate"})
public class MatchDTO {

    private Integer numberMatch;

    private String nameMatch;

    private PlayerDTO playerOne;

    private PlayerDTO playerTwo;

    private PlayerDTO playerWinner;

    private String score;

    private String phase;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
