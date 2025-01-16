package com.example.web_imdb_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class WebImdbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebImdbProjectApplication.class, args);

		// Example usage of readFirst20Lines method
		try {
			BufferedReader reader = new BufferedReader(new FileReader("web-imdb-project/src/main/resources/title_basics.tsv"));
			readFirst20Lines(reader); // Process the first 20 lines
		} catch (IOException e) {
			e.printStackTrace(); // Handle exception properly
		}
	}

	// Method to read and process the first 20 lines from a BufferedReader (no large memory usage)
	public static void readFirst20Lines(BufferedReader reader) throws IOException {
		String line;
		int lineCount = 0;

		while ((line = reader.readLine()) != null && lineCount < 20) {
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

		reader.close(); // Close the reader after use
	}
}
