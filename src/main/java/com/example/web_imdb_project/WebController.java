package com.example.web_imdb_project;

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
    public String searchMovies(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            // Search movies by primary title
            List<Movies> searchResults = moviesRepository.findByPrimaryTitle(query);
            model.addAttribute("movies", searchResults);
        }
        return "search";
    }
}

