package com.movies.directa24;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.directa24.model.Movie;
import com.movies.directa24.service.MovieApiService;
import com.movies.directa24.service.dto.MovieResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MovieApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MovieApiServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMoviesSuccess() throws Exception {
        MovieResponse mockResponse = new MovieResponse();
        mockResponse.setData(List.of(createMovie("Movie 1", "2021"), createMovie("Movie 2", "2022")));
        mockResponse.setTotal_pages(1);

        String jsonResponse = objectMapper.writeValueAsString(mockResponse);
        when(restTemplate.getForObject("https://directa24-movies.wiremockapi.cloud/api/movies/search?page=1", String.class))
                .thenReturn(jsonResponse);

        MovieApiService movieApiService = new MovieApiService(restTemplate);
        List<Movie> movies = movieApiService.getAllMovies();

        assertEquals(2, movies.size());
        assertEquals("Movie 1", movies.get(0).getTitle());
        assertEquals("2021", movies.get(0).getYear());
    }

    @Test
    public void testGetAllMoviesThrowsException() {
        when(restTemplate.getForObject("https://directa24-movies.wiremockapi.cloud/api/movies/search?page=1", String.class))
                .thenThrow(new RuntimeException("API Error"));

        MovieApiService movieApiService = new MovieApiService(restTemplate);

        assertThrows(RuntimeException.class, movieApiService::getAllMovies);
    }

    private Movie createMovie(String title, String year) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        return movie;
    }
}
