package com.pe.walavo.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("configurations")
public class Configuration {

    @Id
    private Long id;

    private String nameChampionship;

    private Short amountMatch;

    private String type;

    private Long firstAward;

    private Long secondAward;

    private LocalDateTime dateChampionship;

}
