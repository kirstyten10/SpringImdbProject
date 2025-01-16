package com.example.web_imdb_project;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Movies {

    private String tconst;
    private String titleType;
    private String primaryTitle;
    private Integer startYear;
    private Integer runtimeMinutes;

    public String getTconst() {
        return tconst;
    }

    public String setTitleType() {
        return titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

}