package com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses.dtos;

import lombok.Builder;

@Builder
public record FetchCoursesRequestDTO(String name, String category) {}
