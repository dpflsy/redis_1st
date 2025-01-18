package com.dpflsy.movie.controller;

import com.dpflsy.common.dto.MovieResponse;
import com.dpflsy.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/api/v1/movies")
    public List<MovieResponse> getMovies(
            @RequestParam(defaultValue = "releaseDate") String sortBy,
            @RequestParam(defaultValue = "true") boolean descending) {
        return movieService.getMovies(sortBy, descending);

    }
}