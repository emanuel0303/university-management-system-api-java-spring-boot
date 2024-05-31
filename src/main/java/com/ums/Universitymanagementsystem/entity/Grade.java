package com.ums.Universitymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer grade_id;

    private Float score;

    @OneToOne
    @JoinColumn(name = "enroll_id", nullable = false)
    private Enrollment enrollment;
}
