package com.example.caloriestracking.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLogin {
    private String name;
    private String email;
    private String avatar;
}
