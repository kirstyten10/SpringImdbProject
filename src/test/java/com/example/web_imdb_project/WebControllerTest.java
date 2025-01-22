package com.example.web_imdb_project;

import com.example.web_imdb_project.controller.WebController;
import com.example.web_imdb_project.model.Movies;
import com.example.web_imdb_project.repository.MoviesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
public class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MoviesRepository moviesRepository;

    @Test
    public void testSearchMovies() throws Exception {
        Movies movie = Movies.builder()
                .tconst("tt1234567")
                .titleType("movie")
                .primaryTitle("Test Movie")
                .build();
        when(moviesRepository.searchMovies("Test")).thenReturn(Collections.singletonList(movie));

        mockMvc.perform(get("/search").param("query", "Test"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("movies"))
                .andExpect(view().name("search"));
    }

    @Test
    public void testShowMovieDetails() throws Exception {
        Movies movie = Movies.builder()
                .tconst("tt1234567")
                .primaryTitle("Test Movie")
                .build();
        when(moviesRepository.findById("tt1234567")).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/movie/tt1234567"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("movie"))
                .andExpect(view().name("movieDetails"));
    }
}
