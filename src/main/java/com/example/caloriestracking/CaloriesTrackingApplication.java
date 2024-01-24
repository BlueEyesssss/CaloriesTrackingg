package com.example.caloriestracking;

import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.entity.User;
import com.example.caloriestracking.repo.FoodRepo;
import com.example.caloriestracking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaloriesTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaloriesTrackingApplication.class, args);
    }

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private UserRepo userRepo;

    @Bean
    CommandLineRunner run(){
        return args -> {
//            foodRepo.save(new Food(1, "Beef", "...", 250, "thit bo description", "Cook by oil, fire", 100, 0, 250, 0, null));
//            foodRepo.save(new Food(2, "Egg", "...", 45, "Egg description", "Cook by oil, fire", 50, 0, 45, 0, null));
//            foodRepo.save(new Food(3, "Carot", "...", 41, "Carot description", "Cook by oil, fire", 100, 0, 41, 0, null));
//
//            User user = new User();
//            user.setUserID(1);
//            user.setFullName("Pham Huynh Phuong Kha (K15 HCM)");
//            user.setAge(20);
//            user.setWeight(75);
//            user.setHeight(175);
//            user.setSex("male");
//            user.setTarget("Losing Weight");
//            user.setAvatar("https://lh3.googleusercontent.com/a/AGNmyxbC41vAPPubI-cjwtxPKU0b-tqE_NI0elq7rOR2=s96-c");
//            user.setEmail("khaphpse151400@fpt.edu.vn");
//            user.setPassword("1");
//            user.setRole(0);
//            user.setPremium(true);
//            user.setProtein(0);
//            user.setCarbs(0);
//            user.setFat(0);
//            userRepo.save(user);
        };
    }
}
