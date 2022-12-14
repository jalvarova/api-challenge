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
@JsonPropertyOrder({"championshipId", "name", "type", "firstAward", "secondAward", "amountMatch", "country", "watchTv", "date", "playerWinner"})
public class ChampionDTO {

    private String championshipId;

    private String name;

    private String type;

    private Double firstAward;

    private Double secondAward;

    private Integer amountMatch;

    private String country;

    private String watchTv;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime date;

    private PlayerDTO playerWinner;
}
