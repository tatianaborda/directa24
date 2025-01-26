package com.movies.directa24.service;
import com.movies.directa24.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieApiService movieApiService;
    private final MovieFilterService movieFilterService;

    public MovieService(MovieApiService movieApiService, MovieFilterService movieFilterService) {
        this.movieApiService = movieApiService;
        this.movieFilterService = movieFilterService;
    }

    public List<String> getDirectorsAboveThreshold(int threshold) {
        List<Movie> allMovies = movieApiService.getAllMovies();
        return movieFilterService.getDirectorsAboveThreshold(allMovies, threshold);
    }

    public List<Movie> getMoviesByYear(int year) {
        List<Movie> allMovies = movieApiService.getAllMovies();
        return movieFilterService.filterMoviesByYear(allMovies, year);
    }
}
