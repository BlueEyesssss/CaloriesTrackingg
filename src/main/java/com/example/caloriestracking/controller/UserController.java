package com.example.caloriestracking.controller;

import com.example.caloriestracking.entity.Exercise;
import com.example.caloriestracking.entity.User;
import com.example.caloriestracking.entity.dto.ResponseObject;
import com.example.caloriestracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("premium/up")
    public ResponseEntity<ResponseObject> updatePremium(int id){
        try{
            if(userService.updatePremium(id)){
                ResponseObject responseObject = new ResponseObject("success", "update premium success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
        return null;
    }

    @GetMapping("premium/down")
    public ResponseEntity<ResponseObject> deletePremium(int id){
        try{
            if(userService.deletePremium(id)){
                ResponseObject responseObject = new ResponseObject("success", "update premium success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getall(){
        try{
            ResponseObject responseObject = new ResponseObject("success", "get all users success", userService.getall());
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @GetMapping("search")
    public ResponseEntity<ResponseObject> searchUsers(@RequestParam String name){
        try{
            List<User> listf = userService.findByName(name.trim());
            ResponseObject responseObject = new ResponseObject("success", "get all user success", listf);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createUser(@Valid @RequestBody User userTmp){
        try{
            boolean rs = userService.createUser(userTmp);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "create user success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "create user fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateUser(@Valid @RequestBody User user){
        try{
            boolean rs = userService.updateUser(user);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "update user success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "update user fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteUser(@RequestParam int id){
        try{
            boolean rs = userService.deleteUser(id);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "delete user success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "delete user fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    //method bắt lỗi valid của các obj
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        // Xử lý lỗi validation và tạo phản hồi tùy ý

        ResponseObject responseObject = new ResponseObject("fail", bindingResult.getFieldError().getDefaultMessage(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
    }
}
