package com.nnk.springboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @NotBlank
    @Column(name = "moodysRating", nullable = false, length = 125)
    private String moodysRating;

    @NotBlank
    @Column(name = "sandPRating", nullable = false, length = 125)
    private String sandPRating;

    @NotBlank
    @Column(name = "fitchRating", nullable = false, length = 125)
    private String fitchRating;

    @NotNull
    @Column(name = "orderNumber", nullable = false)
    private Integer orderNumber;

    @Column(name = "creationDate")
    private Timestamp creationDate;

}
