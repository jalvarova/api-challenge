package com.pe.walavo.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Championship {

    @Id
    private String nameChampionship;

    private Player winner;

    private Long awards;

    private Short amountMatch;

    private String type;

    private List<Match> matches;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
