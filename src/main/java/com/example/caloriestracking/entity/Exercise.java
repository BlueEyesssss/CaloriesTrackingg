package com.example.caloriestracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exerciseID;

    @NotBlank(message = "exercise name cannot be blank")
    @Size(min = 2, max = 100, message = "exercise must length from 2 -> 100 characters")
    private String exerciseName;

    @NotBlank(message = "exercise avatar cannot be blank")
    private String exerciseAvatar;

    @Min(1)
    @Max(9999)
    private double exerciseCalories;

    private double minutes;

    @NotBlank
    @Size(min = 5, message = "description length must > 5 characters")
    private String exerciseDescription;

    @NotBlank
    @Size(min = 5, message = "guide length must > 5 characters")
    private String guide;

    @ManyToMany(mappedBy = "exercises")
    @JsonIgnore
    private List<User> users = new ArrayList<>();   //food liked by user
}
