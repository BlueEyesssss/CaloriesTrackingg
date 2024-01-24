package com.example.caloriestracking.repo;

import com.example.caloriestracking.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrackRepo extends JpaRepository<Track, Integer> {
    @Query("SELECT x FROM Track x WHERE x.user.userID = ?1")
    List<Track> findAllByUserId(int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Track x WHERE x.user.userID = ?1")
    int deleteAllByUserId(int uerID);
}
