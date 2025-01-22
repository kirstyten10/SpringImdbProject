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

                while ((line = reader.readLine()) != null) {
                    // Skip the header row
                    if (lineCount == 0) {
                        lineCount++;
                        continue;
                    }

                    // Split each line by tab character
                    String[] columns = line.split("\t");

                    // Extract the fields
                    String tconst = cleanString(columns[0]);
                    String titleType = cleanString(columns[1]);
                    String primaryTitle = cleanString(columns[2]);
                    String originalTitle = cleanString(columns[3]);

                    if (tconst.isEmpty() || primaryTitle.isEmpty()) {
                        System.out.println("Skipping line due to missing essential data: " + line);
                        continue; // Skip this row if 'tconst' or 'primaryTitle' is empty
                    }

                    if (primaryTitle.length() > 255) {
                        primaryTitle = primaryTitle.substring(0, 255);
                    }

                    if (originalTitle.length() > 255) {
                        originalTitle = originalTitle.substring(0, 255);
                    }

                    boolean isAdult = "1".equals(columns[4].trim());

                    // Parse startYear
                    Integer startYear = parseInteger(columns[5].trim());
                    if (startYear == null) {
                        startYear = 0;
                    }

                    Integer endYear = parseInteger(columns[6]);
                    if (endYear == null) {
                        endYear = 0; // Default to 0 if endYear is missing or invalid
                    }

                    // Parse runtimeMinutes
                    Integer runtimeMinutes = parseInteger(columns[7]);
                    if (runtimeMinutes == null) {
                        continue;
                    }

                    // Clean and validate genres
                    String[] genreArray = cleanGenres(columns[8]);
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

    // Clean strings by trimming and handling nulls
    private String cleanString(String value) {
        return (value != null && !value.trim().isEmpty() && !"\\N".equals(value.trim())) ? value.trim() : "";
    }

    // Clean and handle genres (removes empty or invalid genres)
    private String[] cleanGenres(String genreString) {
        if (genreString == null || genreString.isEmpty() || "\\N".equals(genreString)) {
            return new String[0]; // Return empty array if genres are missing or invalid
        }
        return genreString.split(",");
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
