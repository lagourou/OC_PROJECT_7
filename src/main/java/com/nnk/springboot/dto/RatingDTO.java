package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    private Integer id;

    @NotBlank(message = "moodysRating cannot be empty")
    private String moodysRating;

    @NotBlank(message = "sandPRating cannot be empty")
    private String sandPRating;

    @NotBlank(message = "fitchRating cannot be empty")
    private String fitchRating;

    @NotNull(message = "orderNumber is required")
    @Positive(message = "orderNumber must be positive")
    private Integer orderNumber;

}
