package com.example.web_imdb_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

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

                while ((line = reader.readLine()) != null && processedRows < 100000) {
                    // Skip the header row
                    if (lineCount == 0) {
                        lineCount++;
                        continue;
                    }

                    // Split each line by tab character
                    String[] columns = line.split("\t");

                    // Extract the fields
                    String tconst = columns[0].trim();
                    String titleType = columns[1].trim();
                    String primaryTitle = columns[2].trim();
                    String originalTitle = columns[3].trim();
                    boolean isAdult = "1".equals(columns[4].trim());

                    // Parse startYear
                    Integer startYear = parseInteger(columns[5].trim());
                    if (startYear == null) {
                        startYear = 0;
                    }

                    // Parse endYear
                    Integer endYear = parseInteger(columns[6]);

                    // Parse runtimeMinutes
                    Integer runtimeMinutes = parseInteger(columns[7]);

                    // Parse genres
                    String[] genreArray = columns[8].split(",");
                    List<String> genres = Arrays.asList(genreArray);

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

    private Integer parseInteger(String value) {
        try {
            return (value != null && !value.equals("\\N") && !value.isEmpty())
                    ? Integer.parseInt(value.trim())
                    : null;
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer value: " + value);
            return null;
        }
    }

}
