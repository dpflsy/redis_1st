package com.dpflsy.movie.service;

import com.dpflsy.common.dto.MovieResponse;
import com.dpflsy.movie.mapper.MovieMapper;
import com.dpflsy.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponse> getMovies(String sortBy, boolean descending) {
        Sort sort = descending ? Sort.by(Sort.Direction.DESC, sortBy) : Sort.by(Sort.Direction.ASC, sortBy);

        return movieRepository.findAll(sort)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .collect(Collectors.toList());
    }
}
