package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.Exercise;

import java.util.List;

public interface IExerciseService {
    List<Exercise> getall();
    Exercise getByID(int id);
    boolean createExercise(Exercise e);
    boolean updateExercise(Exercise e);
    boolean deleteExercise(int id);
    List<Exercise> findByName(String name);
}
