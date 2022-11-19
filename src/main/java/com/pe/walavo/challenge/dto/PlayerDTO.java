package com.pe.walavo.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlayerDTO {

    @Pattern(regexp = "[0-9]{8}")
    private String document;

    @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)")
    private String name;

    @Min(value = 1)
    @Max(value = 100)
    private Integer skill;

    @Min(value = 1)
    @Max(value = 100)
    private Integer speed;

    @Min(value = 1)
    @Max(value = 100)
    private Integer strength;

    @Min(value = 0)
    @Max(value = 100)
    private Integer reactionTime;

    @Pattern(regexp = "[M,F]")
    private Character gender;

    @Size(min = 1, max = 20)
    private String country;

    @Min(value = 18, message = "Edad no suficiente")
    @Max(value = 50, message = "Edad no suficiente")
    private Integer age;

    @Min(value = 1)
    @Max(value = 4)
    private Integer phase;
}
