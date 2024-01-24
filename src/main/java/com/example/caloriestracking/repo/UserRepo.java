package com.example.caloriestracking.repo;

import com.example.caloriestracking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);
  
    List<User> findUserByFullNameContaining(String key);
}
