package com.example.web_imdb_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class WebImdbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebImdbProjectApplication.class, args);

		// Example usage of readFirst20Lines method
		try (InputStream inputStream = WebImdbProjectApplication.class
				.getClassLoader()
				.getResourceAsStream("title_basics.tsv");
			 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

			if (reader != null) {
				readFirst20Lines(reader); // Process the first 20 lines
			} else {
				System.err.println("File not found: title_basics.tsv");
			}

		} catch (IOException e) {
			System.err.println("Error reading the file: " + e.getMessage());
		}
	}

	// Method to read and process the first 20 lines from a BufferedReader (no large memory usage)
	public static void readFirst20Lines(BufferedReader reader) throws IOException {
		String line;
		int lineCount = 0;

		while ((line = reader.readLine()) != null && lineCount < 20) {
			// Check if the line is empty
			if (line.trim().isEmpty()) {
				continue;
			}

			// Split each line by the tab character (\t)
			String[] columns = line.split("\t");

			// Process the columns (e.g., print them)
			System.out.println("Line " + (lineCount + 1) + ":");
			for (String column : columns) {
				System.out.print(column + " | "); // Just to demonstrate the data
			}
			System.out.println(); // New line after printing columns
			lineCount++;
		}
	}
}
