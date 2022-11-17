package com.pe.walavo.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "players")
public class Player {

    @Id
    private String document;

    private String name;

    private Integer skill;

    private Character gender;

    private String country;

    private Integer age;

}
