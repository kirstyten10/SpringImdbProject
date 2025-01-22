package com.example.web_imdb_project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, String> {
    @Query("SELECT m FROM Movies m WHERE LOWER(m.primaryTitle) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(m.titleType) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Movies> searchMovies(String query);
}
