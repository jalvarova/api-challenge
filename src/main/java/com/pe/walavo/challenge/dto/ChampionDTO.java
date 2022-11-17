package com.pe.walavo.challenge.dto;

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
@JsonPropertyOrder({"name", "player", "amountMatch", "awards", "date"})
public class ChampionDTO {

    private PlayerDTO playerDTO;

    private String name;

    private Long awards;

    private Short amountMatch;

    private LocalDateTime date;
}
