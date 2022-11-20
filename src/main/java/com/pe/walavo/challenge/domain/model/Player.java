package com.pe.walavo.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "championship.players")
public class Player implements Persistable<String> {

    @Id
    private String document;

    private String name;

    private Integer skill;

    private Character gender;

    private String country;

    private Integer age;

    @Override
    public String getId() {
        return document;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
