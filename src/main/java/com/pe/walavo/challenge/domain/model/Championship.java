package com.pe.walavo.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("championship.championships")
public class Championship implements Persistable<String> {

    @Id
    private String identifier;

    private String configurationName;

    private String country;

    private String watchTv;

    private String winner;

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
