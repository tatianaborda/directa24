package com.movies.directa24.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;

    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @JsonProperty("Year")
    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }
    @JsonProperty("Rated")
    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    @JsonProperty("Released")
    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    @JsonProperty("Runtime")
    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }
    @JsonProperty("Genre")
    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    @JsonProperty("Director")
    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    @JsonProperty("Writer")
    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    @JsonProperty("Actors")
    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }
}