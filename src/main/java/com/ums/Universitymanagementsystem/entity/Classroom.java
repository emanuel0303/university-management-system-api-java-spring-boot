package com.ums.Universitymanagementsystem.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer class_id;

    private String className;

    private Integer  class_capacity;

    @OneToMany(mappedBy = "classroom")
    private Set<TimeTable> timeTables;
}
