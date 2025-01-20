package com.example.web_imdb_project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, String> {
    List<Movies> findByPrimaryTitle(String primaryTitle);
}
