package com.example.web_imdb_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader {

    @Bean
    CommandLineRunner initData(MoviesRepository repository) {
        return args -> {
            repository.save(Movies.builder()
                    .tconst("tt0000001")
                    .titleType("movie")
                    .primaryTitle("Test Movie 1")
                    .startYear(2025)
                    .runtimeMinutes(120)
                    .build());

            repository.save(Movies.builder()
                    .tconst("tt0000002")
                    .titleType("short")
                    .primaryTitle("Test Short 1")
                    .startYear(2023)
                    .runtimeMinutes(15)
                    .build());

            System.out.println("Sample data inserted into H2 database.");
        };
    }
}
