package com.pe.walavo.challenge.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Validated
public class SearchChampionsDTO {

    private String name;

    private String type;

    private LocalDateTime from;

    private LocalDateTime to;

    @Min(value=5, message="must be equal or greater than 5")
    @Max(value=100, message="must be equal or less than 100")
    private int limit;

    @Min(value=0, message="must be equal or greater than 0")
    @Max(value=100, message="must be equal or less than 100")
    private int page;
}
