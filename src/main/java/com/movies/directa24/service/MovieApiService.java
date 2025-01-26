package com.movies.directa24.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.directa24.model.Movie;
import com.movies.directa24.service.dto.MovieResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieApiService {

    private final RestTemplate restTemplate;
    private static final String MOVIE_API_URL = "https://directa24-movies.wiremockapi.cloud/api/movies/search";

    public MovieApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> getAllMovies() {
        try {
            int page = 1;
            List<Movie> allMovies = new ArrayList<>();
            boolean hasNextPage = true;

            while (hasNextPage) {
                String url = MOVIE_API_URL + "?page=" + page;
                String jsonResponse = restTemplate.getForObject(url, String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                MovieResponse movieResponse = objectMapper.readValue(jsonResponse, MovieResponse.class);

                allMovies.addAll(movieResponse.getData());
                hasNextPage = page < movieResponse.getTotal_pages();
                page++;
            }

            return allMovies;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching movies from API", e);
        }
    }
}
