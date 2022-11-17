package com.pe.walavo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDTO {

    @Pattern(regexp = "[^[a-zA-Z]*$]")
    private String name;

    @Min(value = 1)
    @Max(value = 100)
    private Integer skill;

}
