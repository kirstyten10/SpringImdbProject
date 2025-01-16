package com.example.web_imdb_project;

// Java Program to Demonstrate DepartmentService File

// Importing required package modules
//package com.amiya.springbootdemoproject.service;


//import com.amiya.springbootdemoproject.entity.Department;
// Importing required classes
import java.util.List;

// Interface
public interface MoviesService {

    // Save operation
    Movies saveDepartment(Movies movies);

    // Read operation
    List<Movies> fetchDepartmentList();

    // Update operation
    Movies updateDepartment(Movies movies,
                                Long tconst);

    // Delete operation
    void deleteDepartmentById(Long departmentId);
}
