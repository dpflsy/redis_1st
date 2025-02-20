package com.dpflsy.movie.service;

import com.dpflsy.common.dto.MovieResponse;
import com.dpflsy.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MovieResponse> getNowPlayingMovies() {
        return movieRepository.findAllByOrderByReleaseDateDesc()
                .stream()
                .map(movie -> {
                    MovieResponse response = new MovieResponse();
                    response.setId(movie.getId());
                    response.setTitle(movie.getTitle());
                    response.setGenre(movie.getGenre());
                    response.setThumbnailUrl(movie.getThumbnailUrl());
                    response.setReleaseDate(movie.getReleaseDate().toString());
                    response.setRuntime(movie.getRuntime());
                    response.setRating(movie.getRating().getName());
                    response.setSchedules(
                        movie.getSchedules().stream()
                            .map(schedule -> {
                                MovieResponse.ScheduleResponse scheduleResponse = new MovieResponse.ScheduleResponse();
                                scheduleResponse.setId(schedule.getId());
                                scheduleResponse.setStartTime(schedule.getStartTime());
                                scheduleResponse.setEndTime(schedule.getEndTime());
                                return scheduleResponse;
                            })
                            .collect(Collectors.toList())
                    );
                    return response;
                })
                .collect(Collectors.toList());
    }
}
