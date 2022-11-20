package com.pe.walavo.challenge.infraestructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime endDate;
}
