package com.nnk.springboot.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO {

    private Integer tradeId;

    @NotBlank(message = "Account is required")
    private String account;

    @NotBlank(message = "Type is required")
    private String type;

    @NotNull(message = "Buy quantity is required")
    @Positive(message = "Buy quantity must be positive")
    @Digits(integer = 10, fraction = 1)
    private Double buyQuantity;
}
