package com.example.caloriestracking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;
    private float calo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
