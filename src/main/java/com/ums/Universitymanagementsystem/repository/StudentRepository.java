package com.ums.Universitymanagementsystem.repository;
import com.ums.Universitymanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, StudentRepositoryCustom {


}

