package com.marcosparreiras.programmingcourses.modules.course.dtos;

import lombok.Builder;

@Builder
public record UpdateCourseRequestDTO(String id, String name, String category) {}
