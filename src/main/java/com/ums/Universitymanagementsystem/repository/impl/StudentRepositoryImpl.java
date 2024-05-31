package com.ums.Universitymanagementsystem.repository.impl;


import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.entity.Student;
import com.ums.Universitymanagementsystem.repository.StudentRepositoryCustom;
import com.ums.Universitymanagementsystem.request.SearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    private final EntityManager em;
    private final ModelMapper modelMapper;


    @Override
    public List<StudentDTO> findAllBySimpleQuery(String firstName, String lastName, String email) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
        }
        if (lastName != null && !lastName.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Student> query = em.createQuery(criteriaQuery);
        List<Student> students = query.getResultList();


        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(modelMapper.map(student, StudentDTO.class));
        }
        return studentDTOs;
    }

    @Override
    public List<StudentDTO> findAllByCriteria(SearchRequest request) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);


        List<Predicate> predicates = new ArrayList<>();

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + request.getFirstName() + "%"));
        }
        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + request.getLastName() + "%"));
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%"));
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }



        TypedQuery<Student> query = em.createQuery(criteriaQuery);
        List<Student> students = query.getResultList();

        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(modelMapper.map(student, StudentDTO.class));
        }
        return studentDTOs;
    }
}
