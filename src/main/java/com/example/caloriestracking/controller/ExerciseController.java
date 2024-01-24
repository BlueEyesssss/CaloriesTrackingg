package com.example.caloriestracking.controller;

import com.example.caloriestracking.entity.Exercise;
import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.entity.dto.ResponseObject;
import com.example.caloriestracking.service.ExerciseService;
import com.example.caloriestracking.service.FoodService;
import com.example.caloriestracking.service.IExerciseService;
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
@RequestMapping(value = "exercises")
@Validated
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<ResponseObject> getall(){
        try{
            ResponseObject responseObject = new ResponseObject("success", "get all exercise success", exerciseService.getall());
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @GetMapping("search")
    public ResponseEntity<ResponseObject> searchExercise(@RequestParam String name){
        try{
            List<Exercise> listf = exerciseService.findByName(name.trim());
            ResponseObject responseObject = new ResponseObject("success", "get all exercise success", listf);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createExercise(@Valid @RequestBody Exercise exerciseTmp){
        try{
            boolean rs = exerciseService.createExercise(exerciseTmp);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "create exercise success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "create exercise fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseObject> updateExercise(@Valid @RequestBody Exercise exercise){
        try{
            boolean rs = exerciseService.updateExercise(exercise);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "update exercise success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "update exercise fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteExercise(@RequestParam int id){
        try{
            boolean rs = exerciseService.deleteExercise(id);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "delete exercise success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "delete exercise fail", null);
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
