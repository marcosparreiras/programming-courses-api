package com.marcosparreiras.programmingcourses.modules.course.dtos;

import lombok.Builder;

@Builder
public record ToggleCourseIsActiveRequest(String id) {}
