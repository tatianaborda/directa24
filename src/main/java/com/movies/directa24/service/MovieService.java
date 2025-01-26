package com.movies.directa24.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.directa24.model.Movie;
import com.movies.directa24.service.dto.MovieResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    private static final String MOVIE_API_URL = "https://directa24-movies.wiremockapi.cloud/api/movies/search";

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getDirectorsAboveThreshold(int threshold) {
        try {
            int page = 1;
            List<String> allDirectors = new ArrayList<>();
            boolean hasNextPage = true;

            while (hasNextPage) {
                String url = MOVIE_API_URL + "?page=" + page;
                String jsonResponse = restTemplate.getForObject(url, String.class);

                ObjectMapper objectMapper = new ObjectMapper();
                MovieResponse movieResponse = objectMapper.readValue(jsonResponse, MovieResponse.class);

                List<String> directors = movieResponse.getData().stream()
                        .map(Movie::getDirector)
                        .collect(Collectors.toList());
                allDirectors.addAll(directors);

                hasNextPage = page < movieResponse.getTotal_pages();
                page++;
            }

            Map<String, Long> directorCount = allDirectors.stream()
                    .collect(Collectors.groupingBy(director -> director, Collectors.counting()));

            List<String> directorsAboveThreshold = directorCount.entrySet().stream()
                    .filter(entry -> entry.getValue() > threshold)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            Collections.sort(directorsAboveThreshold);

            return directorsAboveThreshold;

        } catch (Exception e) {
            throw new RuntimeException("Error processing API response", e);
        }
    }
}
