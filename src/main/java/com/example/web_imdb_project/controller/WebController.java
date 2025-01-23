package com.example.web_imdb_project.controller;

import com.example.web_imdb_project.model.Movies;
import com.example.web_imdb_project.repository.MoviesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    private final MoviesRepository moviesRepository;

    public WebController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/search")
    public String searchMovies(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "sort", defaultValue = "primaryTitle") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection,
            Model model
    ) {
        Sort.Direction direction = "desc".equals(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy);

        List<Movies> searchResults;

        if (query != null && !query.isEmpty()) {
            searchResults = moviesRepository.searchMovies(query);
        } else {
            searchResults = (List<Movies>) moviesRepository.findAll(sort);
        }

        model.addAttribute("movies", searchResults);

        return "search";
    }
}
