package com.example.web_imdb_project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {

    @Id
    @Column(length = 20, nullable = false, unique = true)
    private String tconst; // Unique identifier for the movie

    @Column(length = 50, nullable = false)
    private String titleType; // Type of title (e.g., movie, short, TV series)

    @Column(length = 255, nullable = false)
    private String primaryTitle; // Main title of the movie

    @Column(length = 255)
    private String originalTitle; // Original title in the original language

    @Column(nullable = false)
    private boolean isAdult; // Whether the title is adult content

    @Column(nullable = false)
    private Integer startYear; // Year the movie started/released

    @Column
    private Integer endYear; // End year for TV Series (nullable for other types)

    @Column(nullable = true)
    private Integer runtimeMinutes; // Duration of the movie in minutes

    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres; // Genres associated with the movie
}

