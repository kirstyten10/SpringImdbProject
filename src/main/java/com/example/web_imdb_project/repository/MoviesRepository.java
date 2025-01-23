package com.example.web_imdb_project.repository;

import com.example.web_imdb_project.model.Movies;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long>, JpaSpecificationExecutor<Movies> {
    @Query("SELECT m FROM Movies m WHERE LOWER(m.primaryTitle) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(m.titleType) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Movies> searchMovies(String query);

    List<Movies> findAll(Sort sort);
}
