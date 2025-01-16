package com.example.web_imdb_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader {

    private final WebImdbProjectApplication application; // Inject WebImdbProjectApplication

    public TestDataLoader(WebImdbProjectApplication application) {
        this.application = application;
    }

    @Bean
    CommandLineRunner initData() {
        return args -> {
            application.readAndSaveData(); // Corrected method call
            System.out.println("Data from title_basics.tsv has been imported.");
        };
    }
}
