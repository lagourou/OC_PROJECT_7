package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleNameDTO {

    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Json is required")
    private String json;

    @NotBlank(message = "Template is required")
    private String template;

    @NotBlank(message = "SQL string is required")
    private String sqlStr;

    @NotBlank(message = "SQL part is required")
    private String sqlPart;

}
