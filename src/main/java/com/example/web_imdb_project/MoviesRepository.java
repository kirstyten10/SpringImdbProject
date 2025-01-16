package com.example.web_imdb_project;

//import com.amiya.springbootdemoproject.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

// Interface extending CrudRepository
public interface MoviesRepository
        extends CrudRepository<Movies, Long> {
}
