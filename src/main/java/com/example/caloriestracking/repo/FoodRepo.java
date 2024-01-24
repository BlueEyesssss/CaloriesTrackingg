package com.example.caloriestracking.repo;

import com.example.caloriestracking.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Enumeration;
import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {

    List<Food> findByFoodNameContaining(String key);
}
