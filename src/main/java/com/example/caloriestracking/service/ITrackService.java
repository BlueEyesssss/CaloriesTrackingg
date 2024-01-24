package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.Track;

import java.util.List;

public interface ITrackService {
    List<Track> allTrackOfUser(int userId);
    boolean create(Track track);
    boolean update(Track track);
    boolean delete(int id);
    boolean deleteByUserId(int userid);
}
