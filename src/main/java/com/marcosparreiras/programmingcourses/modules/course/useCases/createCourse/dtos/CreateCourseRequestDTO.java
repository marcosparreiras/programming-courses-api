package com.marcosparreiras.programmingcourses.modules.course.useCases.createCourse.dtos;

import lombok.Builder;

@Builder
public record CreateCourseRequestDTO(String name, String category) {}
