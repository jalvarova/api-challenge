package com.pe.walavo.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("championship.matches")
public class Match implements Persistable<String> {

    @Id
    @Column("identifier")
    private String identifier;

    private Integer numberMatch;
    
    private String nameMatch;

    private String championship;

    private String playerOne;

    private String playerTwo;

    private String playerWinner;

    private String score;

    private Integer phase;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Override
    public String getId() {
        return identifier;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
