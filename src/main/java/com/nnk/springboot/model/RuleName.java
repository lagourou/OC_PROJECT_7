package com.nnk.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 125)
    private String name;

    @NotBlank
    @Column(name = "description", nullable = false, length = 125)
    private String description;

    @NotBlank
    @Column(name = "json", nullable = false, length = 125)
    private String json;

    @NotBlank
    @Column(name = "template", nullable = false, length = 512)
    private String template;

    @NotBlank
    @Column(name = "sqlStr", nullable = false, length = 125)
    private String sqlStr;

    @NotBlank
    @Column(name = "sqlPart", nullable = false, length = 125)
    private String sqlPart;

    @Column(name = "creationDate")
    private Timestamp creationDate;

}
