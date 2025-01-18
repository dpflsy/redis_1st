package com.dpflsy.common.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String rating,
        String genre,
        String thumbnailUrl,
        String releaseDate,
        Integer runtime,
        List<ScheduleResponse> schedules
) {
    @Builder
    public record ScheduleResponse(
            Long id,
            String startTime,
            String endTime
    ) {}
}
