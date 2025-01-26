package com.movies.directa24.service;

import com.movies.directa24.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieFilterService {

    public List<Movie> filterMoviesByYear(List<Movie> movies, int year) {
        return movies.stream()
                .filter(movie -> {
                    try {
                        return Integer.parseInt(movie.getYear()) == year;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<String> getDirectorsAboveThreshold(List<Movie> movies, int threshold) {
        Map<String, Long> directorCount = movies.stream()
                .map(Movie::getDirector)
                .collect(Collectors.groupingBy(director -> director, Collectors.counting()));

        return directorCount.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }
}
