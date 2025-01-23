package com.example.web_imdb_project.controller;

import com.example.web_imdb_project.model.Movies;
import com.example.web_imdb_project.repository.MoviesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @RequestParam(name = "query", required = false) String searchQuery,
            @RequestParam(name = "sort", defaultValue = "primaryTitle") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection,
            @RequestParam(name = "isAdult", required = false) Boolean isAdult,
            @RequestParam(name = "titleType", required = false) String titleType,
            Model model
    ) {
        Sort.Direction direction = "desc".equals(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortBy);

        Specification<Movies> spec = Specification.where(null);

        if (searchQuery != null && !searchQuery.isEmpty()) {
            spec = spec.and((root, query, builder) ->
                    builder.like(root.get("primaryTitle"), "%" + searchQuery + "%"));
        }

        if (isAdult != null) {
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("isAdult"), isAdult));
        }

        if (titleType != null && !titleType.isEmpty()) {
            spec = spec.and((root, query, builder) ->
                    builder.equal(root.get("titleType"), titleType));
        }

        List<Movies> searchResults;
        if (spec != null) {
            searchResults = (List<Movies>) moviesRepository.findAll(spec, sort);
        } else {
            searchResults = (List<Movies>) moviesRepository.findAll(sort);
        }

        // Add the results to the model
        model.addAttribute("movies", searchResults);

        return "search";
    }

    @GetMapping("/movie/{tconst}")
    public String viewMovieDetails(@PathVariable String tconst, Model model) {
        Movies movie = moviesRepository.findByTconst(tconst);  // Assuming there's a method to find by tconst
        if (movie != null) {
            model.addAttribute("movie", movie);
            return "movieDetails";  // This is the view where you display the movie details
        }
        return "error";  // Error page if the movie is not found
    }
}