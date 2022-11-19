package com.pe.walavo.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("championship.configurations")
public class Configuration {

    @Id
    @Column("configuration_id")
    private Long id;

    private String name;

    private String description;

    private Integer amountMatch;

    private String type;

    private Double firstAward;

    private Double secondAward;

    private LocalDateTime dateChampionship;

}
