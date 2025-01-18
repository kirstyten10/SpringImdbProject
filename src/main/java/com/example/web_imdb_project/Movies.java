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
    private String tconst;

    @Column(length = 50, nullable = false)
    private String titleType;

    @Column(length = 255, nullable = false)
    private String primaryTitle;

    @Column(length = 255)
    private String originalTitle;

    @Column(nullable = false)
    private boolean isAdult;

    @Column(nullable = true)
    private Integer startYear;

    @Column
    private Integer endYear;

    @Column(nullable = true)
    private Integer runtimeMinutes;

    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;
}

