package com.pe.walavo.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Match {

    @Id
    private String identifier;

    private String nameMatch;

    private Championship championship;

    private Player playerOne;

    private Player playerTwo;

    private Player playerWinner;

    private String score;

    private short phase;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
