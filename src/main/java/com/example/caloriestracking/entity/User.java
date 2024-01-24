package com.example.caloriestracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @NotBlank(message = "fullName Not Blank")
    @Size(min = 5, max = 65, message = "foodName must length from 5 ->  65 characters")
    private String fullName;

    @Min(1)
    @Max(150)
    private int age;

    @DecimalMin(value = "0.0", message = "weight must > 0.0")
    @DecimalMax(value = "400.0", message = "weight must < 400.0 kg")  //ko quá 400kg
    private double weight;

    @DecimalMin(value = "0.0", message = "height must > 0.0")
    @DecimalMax(value = "300.0", message = "height must < 300.0 cm")
    private double height;

    @NotBlank(message = "sex must not null")
    private String sex;

    @NotBlank(message = "target must not null")
    private String target;

    @NotBlank(message = "avatar must not null")
    private String avatar;

    @NotBlank(message = "email must not null")
    @Email(message = "email not correct format")
    private String email;

    @NotBlank(message = "password must not null")
    private String password;

    private int role;   //0:user, 1:admin

    private boolean premium;

    private double Carbs;       //này hình như là để dự đoán cân năng
    private double protein;     //này hình như là để dự đoán cân năng
    private double fat;         //này hình như là để dự đoán cân năng

    @ManyToMany
    @JoinTable(
            name = "user_food",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    @JsonIgnore
    private List<Food> foods = new ArrayList<>();   //user likes food

    @ManyToMany
    @JoinTable(
            name = "user_exercise",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    @JsonIgnore
    private List<Exercise> exercises = new ArrayList<>();   //user likes exercise

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Track> tracks;
}
