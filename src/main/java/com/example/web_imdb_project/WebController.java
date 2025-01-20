package com.example.web_imdb_project;

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
    public String searchMovies(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            List<Movies> searchResults = moviesRepository.searchMovies(query);
            model.addAttribute("movies", searchResults);
        }
        return "search";
    }

    @GetMapping("/movie/{tconst}")
    public String showMovieDetails(@PathVariable("tconst") String tconst, Model model) {
        Movies movie = moviesRepository.findById(tconst).orElse(null);
        if (movie != null) {
            model.addAttribute("movie", movie);
            return "movieDetails";
        }
        model.addAttribute("error", "Movie not found.");
        return "error";
    }

    /*
    public String searchParam() {
        @Query("SELECT u FROM User u WHERE u.status = 1")
        Collection<Movies> findAllActiveUsers();
    }
    */

}
