package com.example.web_imdb_project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, String> {
    // You can define custom query methods here if necessary
    Optional<Movies> findByTconst(String tconst);

    boolean existsByTconst(String tconst);

}
