package com.example.caloriestracking.service;

import com.example.caloriestracking.entity.Track;
import com.example.caloriestracking.repo.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackService implements ITrackService {
    @Autowired
    private TrackRepo trackRepo;
    @Override
    public List<Track> allTrackOfUser(int userId) {
        List<Track> list = trackRepo.findAllByUserId(userId);
        return list;
    }

    @Override
    public boolean create(Track track) {
        trackRepo.save(track);
        return true;
    }

    @Override
    public boolean update(Track track) {
        trackRepo.save(track);
        return true;
    }

    @Override
    public boolean delete(int id) {
        trackRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteByUserId(int userid) {
        trackRepo.deleteAllByUserId(userid);
        return true;
    }
}
