package com.ums.Universitymanagementsystem.repository;

import com.ums.Universitymanagementsystem.entity.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
}
