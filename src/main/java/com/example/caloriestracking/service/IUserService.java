package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.User;

import java.util.List;

public interface IUserService {
    User findByEmail(String email);
    User checkLogin(String email, String pass);
    List<User> getall();
    User getByID(int id);
    boolean createUser(User u);
    boolean updateUser(User u);
    boolean deleteUser(int id);
    List<User> findByName(String name);

    boolean updatePremium(int id);
    boolean deletePremium(int id);
}
