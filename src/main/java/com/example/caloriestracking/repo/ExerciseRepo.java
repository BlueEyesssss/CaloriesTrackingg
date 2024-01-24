package com.example.caloriestracking.repo;

import com.example.caloriestracking.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepo extends JpaRepository<Exercise, Integer> {
    List<Exercise> findByExerciseNameContaining(String key);
}
