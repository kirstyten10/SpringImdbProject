package com.example.web_imdb_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String tconst;

    private String titleType;

    private String primaryTitle;

    private String originalTitle;

    private boolean isAdult;

    private Integer startYear;

    private Integer endYear;

    private Integer runtimeMinutes;
}

