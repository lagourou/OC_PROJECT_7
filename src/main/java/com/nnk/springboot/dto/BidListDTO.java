package com.nnk.springboot.dto;

import java.math.BigDecimal;

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
public class BidListDTO {

    private Integer bidListId;

    @NotBlank(message = "Account cannot be empty")
    private String account;

    @NotBlank(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Bid quantity is required")
    @Positive(message = "Bid quantity must be positive")
    @Digits(integer = 10, fraction = 1)
    private BigDecimal bidQuantity;

}
