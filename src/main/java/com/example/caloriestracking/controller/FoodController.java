package com.example.caloriestracking.controller;

import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.entity.dto.FoodInput;
import com.example.caloriestracking.entity.dto.ResponseObject;
import com.example.caloriestracking.service.FoodService;
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
@RequestMapping(value = "foods")
@Validated
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<ResponseObject> getall(){
        try{
            ResponseObject responseObject = new ResponseObject("success", "get all food success", foodService.getall());
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @GetMapping("search")
    public ResponseEntity<ResponseObject> searchFood(@RequestParam String name){
        try{
            List<Food> listf = foodService.findByName(name.trim());
            ResponseObject responseObject = new ResponseObject("success", "get all food success", listf);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createFood(@Valid @RequestBody Food foodTmp){
        try{
            //Food food = tìm cách ánh xạ qua
            boolean rs = foodService.createFood(foodTmp);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "create food success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "create food fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateFood(@Valid @RequestBody Food food){
        try{
            boolean rs = foodService.updateFood(food);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "update food success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "update food fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteFood(@RequestParam int id){
        try{
            boolean rs = foodService.deleteFood(id);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "delete food success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "delete food fail", null);
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
