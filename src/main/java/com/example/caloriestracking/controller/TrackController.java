package com.example.caloriestracking.controller;

import com.example.caloriestracking.entity.Food;
import com.example.caloriestracking.entity.Track;
import com.example.caloriestracking.entity.dto.ResponseObject;
import com.example.caloriestracking.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "track")
public class TrackController {
    @Autowired
    private ITrackService iTrackService;

    @GetMapping("user")
    public ResponseEntity<ResponseObject> getallTrackByuserid(@RequestParam int userid){
        try{
            List<Track> list = iTrackService.allTrackOfUser(userid);
            ResponseObject responseObject = new ResponseObject("success", "get all track of user success", list);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

//    {
//        "date": "2023-07-04",
//        "calo": 10,
//            "user": {
//              "userID": 1
//              }
//    }
    @PostMapping
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody Track track){
        try{
            //Food food = tìm cách ánh xạ qua
            boolean rs = iTrackService.create(track);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "create success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "create fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

//    {
//        "id": 2,
//        "date": "2023-07-05",
//        "calo": 9,
//            "user": {
//                  "userID": 1
//              }
//    }
    @PutMapping
    public ResponseEntity<ResponseObject> update(@Valid @RequestBody Track track){
        try{
            boolean rs = iTrackService.update(track);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "update success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "update fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> delete(@RequestParam int id){
        try{
            boolean rs = iTrackService.delete(id);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "delete success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "delete fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }

    @DeleteMapping("user")
    public ResponseEntity<ResponseObject> deleteAllByUserID(@RequestParam int id){
        try{
            boolean rs = iTrackService.deleteByUserId(id);
            if(rs){
                ResponseObject responseObject = new ResponseObject("success", "delete success", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }else {
                ResponseObject responseObject = new ResponseObject("fail", "delete fail", null);
                return ResponseEntity.status(HttpStatus.OK).body(responseObject);
            }
        }catch (Exception ex){
            ResponseObject responseObject = new ResponseObject("fail", ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
        }
    }
}
