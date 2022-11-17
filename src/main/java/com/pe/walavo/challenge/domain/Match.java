package com.pe.walavo.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {

    @Id
    private String nameMatch;

    private Player playerOne;

    private Player playerTwo;

    private Player playerWinner;

    private String score;

    private String phase;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
