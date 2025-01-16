package com.example.web_imdb_project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key, auto-generated

    @Column(length = 20, nullable = false, unique = true)
    private String tconst; // Unique identifier for the movie

    @Column(length = 50, nullable = false)
    private String titleType; // Type of title (e.g., movie, short, TV series)

    @Column(length = 255, nullable = false)
    private String primaryTitle; // Main title of the movie

    @Column(nullable = false)
    private Integer startYear; // Year the movie started/released

    @Column(nullable = true)
    private Integer runtimeMinutes; // Duration of the movie in minutes
}
