package com.example.web_imdb_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootApplication
public class WebImdbProjectApplication {

    @Autowired
    private MoviesRepository moviesRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebImdbProjectApplication.class, args);
    }

    public void readAndSaveData() {
        try (InputStream inputStream = WebImdbProjectApplication.class
                .getClassLoader().getResourceAsStream("title_basics.tsv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (reader != null) {
                String line;
                int lineCount = 0;
                int processedRows = 0;

                while ((line = reader.readLine()) != null && processedRows < 200) {
                    // Skip the header row
                    if (lineCount == 0) {
                        lineCount++;
                        continue;
                    }

                    // Split each line by tab character
                    String[] columns = line.split("\t");

                    //MIGHT NOT NEED
                    // Skip empty lines and invalid data
                    //if (columns.length < 5 || line.trim().isEmpty()) {
                    //	continue;
                    //}

                    // Extract the fields
                    String tconst = columns[0].trim();
                    String titleType = columns[1].trim();
                    String primaryTitle = columns[2].trim();
                    String originalTitle = columns[3].trim();
                    boolean isAdult = false;
                    int startYear = 0;
                    int endYear = 0;
                    int runtimeMinutes = 0;

					/* MIGHT NOT NEED
					// Parse startYear safely
					try {
						startYear = columns[5].isEmpty() ? 0 : Integer.parseInt(columns[5].trim());
					} catch (NumberFormatException e) {
						System.err.println("Invalid startYear value: " + columns[5] + " for tconst: " + tconst);
					}

					// Parse runtimeMinutes safely
					try {
						runtimeMinutes = columns[8].isEmpty() ? null : Integer.parseInt(columns[8].trim());
					} catch (NumberFormatException e) {
						System.err.println("Invalid runtimeMinutes value: " + columns[8] + " for tconst: " + tconst);
					}

					 */

                    // Check for duplicates before saving
                    if (!moviesRepository.existsByTconst(tconst)) {
                        // Save to the database
                        moviesRepository.save(Movies.builder()
                                .tconst(tconst)
                                .titleType(titleType)
                                .primaryTitle(primaryTitle)
                                .originalTitle(originalTitle)
                                .isAdult(isAdult)
                                .startYear(startYear)
                                .endYear(endYear)
                                .runtimeMinutes(runtimeMinutes)
                                .build());
                        processedRows++;
                    } else {
                        System.out.println("Skipping duplicate entry for tconst: " + tconst);
                    }

                    lineCount++;
                }

                System.out.println("Processed " + processedRows + " rows.");
            } else {
                System.err.println("File not found: title_basics.tsv");
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
