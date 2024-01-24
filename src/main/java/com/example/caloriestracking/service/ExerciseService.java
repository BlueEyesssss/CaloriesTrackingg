package com.example.caloriestracking.service;


import com.example.caloriestracking.entity.Exercise;
import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.repo.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService implements IExerciseService{
    @Autowired
    private ExerciseRepo exerciseRepo;


    @Override
    public List<Exercise> getall() {
        return exerciseRepo.findAll();
    }

    @Override
    public Exercise getByID(int id) {
        return exerciseRepo.findById(id).orElse(null);
    }

    @Override
    public boolean createExercise(Exercise e) {
        exerciseRepo.save(e);
        return true;
    }

    @Override
    public boolean updateExercise(Exercise e) {
        exerciseRepo.save(e);
        return true;
    }

    @Override
    public boolean deleteExercise(int id) {
        exerciseRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Exercise> findByName(String name) {
        return exerciseRepo.findByExerciseNameContaining(name);
    }
}
