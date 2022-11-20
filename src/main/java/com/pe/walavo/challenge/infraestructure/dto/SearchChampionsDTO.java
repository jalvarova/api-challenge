package com.pe.walavo.challenge.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchChampionsDTO {

    private String name;

    private String type;

    private LocalDateTime from;

    private LocalDateTime to;

    private int limit;

    private int page;
}
