package com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses.dtos;

import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import java.util.List;

public record FetchCoursesResponseDTO(List<Course> courses) {}
