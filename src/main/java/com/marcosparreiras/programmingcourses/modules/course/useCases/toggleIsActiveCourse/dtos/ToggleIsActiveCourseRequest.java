package com.marcosparreiras.programmingcourses.modules.course.useCases.toggleIsActiveCourse.dtos;

import lombok.Builder;

@Builder
public record ToggleIsActiveCourseRequest(String id) {}
