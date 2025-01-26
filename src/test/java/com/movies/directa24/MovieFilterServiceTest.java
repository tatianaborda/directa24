package com.movies.directa24;
import com.movies.directa24.model.Movie;
import com.movies.directa24.service.MovieFilterService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieFilterServiceTest {

    private final MovieFilterService movieFilterService = new MovieFilterService();

    @Test
    public void testFilterMoviesByYear() {
        List<Movie> movies = List.of(
                createMovie("Movie 1", "2021"),
                createMovie("Movie 2", "2022"),
                createMovie("Movie 3", "2021")
        );

        List<Movie> filteredMovies = movieFilterService.filterMoviesByYear(movies, 2021);

        assertEquals(2, filteredMovies.size());
        assertEquals("Movie 1", filteredMovies.get(0).getTitle());
        assertEquals("Movie 3", filteredMovies.get(1).getTitle());
    }

    @Test
    public void testGetDirectorsAboveThreshold() {
        List<Movie> movies = List.of(
                createMovieWithDirector("Movie 1", "2021", "Director A"),
                createMovieWithDirector("Movie 2", "2021", "Director B"),
                createMovieWithDirector("Movie 3", "2021", "Director A")
        );

        List<String> directors = movieFilterService.getDirectorsAboveThreshold(movies, 1);

        assertEquals(1, directors.size());
        assertEquals("Director A", directors.get(0));
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
