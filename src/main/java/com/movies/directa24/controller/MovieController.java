package com.movies.directa24.controller;

import com.movies.directa24.service.MovieService;
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
    public List<String> getDirectorsAboveThreshold(
            @RequestParam int threshold) {
        return movieService.getDirectorsAboveThreshold(threshold);
    }

}