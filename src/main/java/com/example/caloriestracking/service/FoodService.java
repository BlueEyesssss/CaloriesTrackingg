package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService{
    @Autowired
    private FoodRepo foodRepo;


    @Override
    public List<Food> getall() {
        return foodRepo.findAll();
    }

    @Override
    public Food getByID(int id) {
        return foodRepo.findById(id).orElse(null);
    }

    @Override
    public boolean createFood(Food f) {
        foodRepo.save(f);
        return true;
    }

    @Override
    public boolean updateFood(Food f) {
        foodRepo.save(f);
        return true;
    }

    @Override
    public boolean deleteFood(int id) {
        foodRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Food> findByName(String name) {
        return foodRepo.findByFoodNameContaining(name);
    }
}
