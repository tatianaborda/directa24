package com.movies.directa24.controller;

import com.movies.directa24.model.Movie;
import com.movies.directa24.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/directors")
    public ResponseEntity<?> getDirectorsAboveThreshold(@RequestParam int threshold) {
        List<String> directors = movieService.getDirectorsAboveThreshold(threshold);
        if (directors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No directors found with movies above the threshold of " + threshold);
        }
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/api/by-year")
    public ResponseEntity<?> getMoviesByYear(@RequestParam int year) {
        List<Movie> movies = movieService.getMoviesByYear(year);

        if (movies.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No movies found for the year " + year);
        }
        return ResponseEntity.ok(movies);
    }
}