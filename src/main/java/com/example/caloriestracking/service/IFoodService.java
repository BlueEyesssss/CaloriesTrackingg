package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.Food;

import java.util.List;

public interface IFoodService {
    List<Food> getall();
    Food getByID(int id);
    boolean createFood(Food f);
    boolean updateFood(Food f);
    boolean deleteFood(int id);
    List<Food> findByName(String name);
}
