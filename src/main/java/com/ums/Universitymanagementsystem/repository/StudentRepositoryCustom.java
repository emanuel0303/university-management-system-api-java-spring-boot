package com.ums.Universitymanagementsystem.repository;

import com.ums.Universitymanagementsystem.dto.StudentDTO;
import com.ums.Universitymanagementsystem.request.SearchRequest;

import java.util.List;

public interface StudentRepositoryCustom {

    List<StudentDTO> findAllBySimpleQuery(
            String firstName,
            String lastName,
            String email
    );

    List<StudentDTO> findAllByCriteria(
            SearchRequest request
    );

}
