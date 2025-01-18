package com.dpflsy.movie.mapper;

import com.dpflsy.common.dto.MovieResponse;
import com.dpflsy.movie.entity.Movie;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class MovieMapper {


    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .rating(movie.getRating().getName())
                .genre(movie.getGenre())
                .thumbnailUrl(movie.getThumbnailUrl())
                .releaseDate(movie.getReleaseDate().toString())
                .runtime(movie.getRuntime())
                .schedules(
                        movie.getSchedules().stream()
                                .map(schedule -> MovieResponse.ScheduleResponse.builder()
                                        .id(schedule.getId())
                                        .startTime(schedule.getStartTime().format(TIME_FORMATTER)) // 시간만 반환
                                        .endTime(schedule.getEndTime().format(TIME_FORMATTER))     // 시간만 반환
                                        .build()
                                )
                                .collect(Collectors.toList())
                )
                .build();
    }
}
