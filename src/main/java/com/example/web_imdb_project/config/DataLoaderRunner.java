package com.example.web_imdb_project.config;

import com.example.web_imdb_project.service.DataLoaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderRunner implements CommandLineRunner {

    private final DataLoaderService dataLoaderService;

    public DataLoaderRunner(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Call the readAndSaveData method to read the file and insert data into H2
        dataLoaderService.readAndSaveData();
    }
}
