package com.example.web_imdb_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MoviesRepositoryTest {
    @Autowired
    private MoviesRepository moviesRepository;

    @Test
    public void testSearchMovies() {
        Movies movie = Movies.builder()
                .tconst("tt1234567")
                .titleType("movie")
                .primaryTitle("Test Movie")
                .originalTitle("Test Movie Original")
                .isAdult(false)
                .startYear(2020)
                .runtimeMinutes(120)
                .build();
        moviesRepository.save(movie);

        List<Movies> results = moviesRepository.searchMovies("Test");

        assertEquals(1, results.size());
        assertEquals("Test Movie", results.get(0).getPrimaryTitle());
    }

    @Test
    public void testNoResults() {
        List<Movies> results = moviesRepository.searchMovies("NonExistent");

        assertTrue(results.isEmpty());
    }
}
