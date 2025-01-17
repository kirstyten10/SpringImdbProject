package com.example.web_imdb_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderRunner implements CommandLineRunner {

    private final WebImdbProjectApplication webImdbProjectApplication;

    public DataLoaderRunner(WebImdbProjectApplication webImdbProjectApplication) {
        this.webImdbProjectApplication = webImdbProjectApplication;
    }

    @Override
    public void run(String... args) throws Exception {
        // Call the readAndSaveData method to read the file and insert data into H2
        webImdbProjectApplication.readAndSaveData();
    }
}
