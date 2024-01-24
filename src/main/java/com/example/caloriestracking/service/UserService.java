package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.User;
import com.example.caloriestracking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo repo;

    @Override
    public User findByEmail(String email) {
        return repo.findUserByEmail(email);
    }

    @Override
    public User checkLogin(String email, String pass) {
        return repo.findUserByEmailAndPassword(email, pass);
    }

    @Override
    public List<User> getall() {
        return repo.findAll();
    }

    @Override
    public User getByID(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean createUser(User u) {
        repo.save(u);
        return true;
    }

    @Override
    public boolean updateUser(User u) {
        repo.save(u);
        return true;
    }

    @Override
    public boolean deleteUser(int id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public List<User> findByName(String name) {
        return repo.findUserByFullNameContaining(name);
    }

    @Override
    public boolean updatePremium(int id) {
        User user = repo.findById(id).orElse(null);
        if(user != null){
            user.setPremium(true);
            repo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePremium(int id) {
        User user = repo.findById(id).orElse(null);
        if(user != null){
            user.setPremium(false);
            repo.save(user);
            return true;
        }
        return false;
    }
}
