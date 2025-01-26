package com.movies.directa24;

import com.movies.directa24.controller.MovieController;
import com.movies.directa24.model.Movie;
import com.movies.directa24.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class MovieControllerTest {

    private final MovieService movieService = Mockito.mock(MovieService.class);
    private final MovieController movieController = new MovieController(movieService);

    @Test
    void testGetMoviesByYear_withResults() {
        int year = 2015;
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());

        when(movieService.getMoviesByYear(year)).thenReturn(movies);

        ResponseEntity<?> response = movieController.getMoviesByYear(year);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
        verify(movieService, times(1)).getMoviesByYear(year);
    }

    @Test
    void testGetMoviesByYear_noResults() {
        int year = 2020;
        when(movieService.getMoviesByYear(year)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = movieController.getMoviesByYear(year);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No movies found for the year " + year, response.getBody());
        verify(movieService, times(1)).getMoviesByYear(year);
    }

    @Test
    void testGetDirectorsAboveThreshold_withResults() {
        int threshold = 5;
        List<String> directors = List.of("Director A", "Director B");
        when(movieService.getDirectorsAboveThreshold(threshold)).thenReturn(directors);

        ResponseEntity<?> response = movieController.getDirectorsAboveThreshold(threshold);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(directors, response.getBody());
        verify(movieService, times(1)).getDirectorsAboveThreshold(threshold);
    }

    @Test
    void testGetDirectorsAboveThreshold_noResults() {
        int threshold = 50;
        when(movieService.getDirectorsAboveThreshold(threshold)).thenReturn(new ArrayList<>());

        ResponseEntity<?> response = movieController.getDirectorsAboveThreshold(threshold);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No directors found with movies above the threshold of " + threshold, response.getBody());
        verify(movieService, times(1)).getDirectorsAboveThreshold(threshold);
    }
}
