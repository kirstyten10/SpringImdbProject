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
    private String tconst;

    private String titleType;

    private String primaryTitle;

    private String originalTitle;

    private boolean isAdult;

    private Integer startYear;

    private Integer endYear;

    private Integer runtimeMinutes;

    @ElementCollection
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "genre")
    private List<String> genres;
}

