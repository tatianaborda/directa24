package com.movies.directa24;

import com.movies.directa24.model.Movie;
import com.movies.directa24.service.MovieApiService;
import com.movies.directa24.service.MovieFilterService;
import com.movies.directa24.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    @Mock
    private MovieApiService movieApiService;

    @Mock
    private MovieFilterService movieFilterService;

    public MovieServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDirectorsAboveThreshold() {
        List<Movie> movies = List.of(
                createMovieWithDirector("Movie 1", "2021", "Director A"),
                createMovieWithDirector("Movie 2", "2021", "Director B"),
                createMovieWithDirector("Movie 3", "2021", "Director A")
        );

        when(movieApiService.getAllMovies()).thenReturn(movies);
        when(movieFilterService.getDirectorsAboveThreshold(movies, 1)).thenReturn(List.of("Director A"));

        MovieService movieService = new MovieService(movieApiService, movieFilterService);
        List<String> directors = movieService.getDirectorsAboveThreshold(1);

        assertEquals(1, directors.size());
        assertEquals("Director A", directors.get(0));
    }

    @Test
    public void testGetMoviesByYear() {
        List<Movie> movies = List.of(
                createMovie("Movie 1", "2021"),
                createMovie("Movie 2", "2022"),
                createMovie("Movie 3", "2021")
        );

        when(movieApiService.getAllMovies()).thenReturn(movies);
        when(movieFilterService.filterMoviesByYear(movies, 2021)).thenReturn(
                List.of(movies.get(0), movies.get(2))
        );

        MovieService movieService = new MovieService(movieApiService, movieFilterService);
        List<Movie> filteredMovies = movieService.getMoviesByYear(2021);

        assertEquals(2, filteredMovies.size());
        assertEquals("Movie 1", filteredMovies.get(0).getTitle());
        assertEquals("Movie 3", filteredMovies.get(1).getTitle());
    }

    private Movie createMovie(String title, String year) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        return movie;
    }

    private Movie createMovieWithDirector(String title, String year, String director) {
        Movie movie = createMovie(title, year);
        movie.setDirector(director);
        return movie;
    }
}
