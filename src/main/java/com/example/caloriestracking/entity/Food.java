package com.example.caloriestracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodID;

    @NotBlank(message = "foodName Not Blank")
    @Size(min = 2, max = 65, message = "foodName must length from 5 -> 46 characters")
    private String foodName;

    @NotBlank(message = "foodAvatar Not Blank")
    private String foodAvatar;

    @Min(1)
    @Max(9999)
    private double foodCalories;

    @NotBlank
    @Size(min = 5, message = "description length must > 5 characters")
    private String foodDescription;

    @NotBlank
    @Size(min = 5, message = "recipe length must > 5 characters")
    private String recipe;

    @DecimalMin(value = "0.0", message = "foodWeight must > 0.0")
    @DecimalMax(value = "10000.0", message = "foodWeight must < 10000.0")  //ko quá 10kg
    private double foodWeight;      //gram

    @DecimalMin(value = "0.0", message = "foodCarbs must > 0")
    @DecimalMax(value = "1000.0", message = "foodCarbs must < 1000")   //ko quá 1 kg
    private double foodCarbs;   //tinh bột

    @DecimalMin(value = "0.0", message = "foodProtein must > 0")
    @DecimalMax(value = "1000.0", message = "foodProtein must < 1000")   //ko quá 1 kg
    private double foodProtein; //

    @DecimalMin(value = "0.0", message = "foodFat must > 0")
    @DecimalMax(value = "1000.0", message = "foodFat must < 1000")   //ko quá 1 kg
    private double foodFat;     //chất béo

    @ManyToMany(mappedBy = "foods")
    @JsonIgnore
    private List<User> users = new ArrayList<>();   //food liked by user
}
