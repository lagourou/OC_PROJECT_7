package com.nnk.springboot.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurvePointDTO {

    private Integer id;

    @NotNull
    private Integer curveId;

    @NotNull(message = "Term is required")
    @Digits(integer = 10, fraction = 1)
    @Positive(message = "Term must be positive")
    private Double term;

    @NotNull(message = "Value is required")
    @Digits(integer = 10, fraction = 1)
    @Positive(message = "Value must be positive")
    private Double value;

}