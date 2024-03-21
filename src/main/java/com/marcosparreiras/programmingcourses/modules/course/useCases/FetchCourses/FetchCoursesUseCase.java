package com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses;

import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses.dtos.FetchCoursesRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.FetchCourses.dtos.FetchCoursesResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchCoursesUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public FetchCoursesResponseDTO execute(
    FetchCoursesRequestDTO fetchCoursesRequestDTO
  ) {
    List<Course> courses;
    if (
      fetchCoursesRequestDTO.name() != null ||
      fetchCoursesRequestDTO.category() != null
    ) {
      courses =
        this.courseRepository.findByNameOrCategoryContaining(
            fetchCoursesRequestDTO.name(),
            fetchCoursesRequestDTO.category()
          );
      return new FetchCoursesResponseDTO(courses);
    }
    courses = this.courseRepository.findAll();
    return new FetchCoursesResponseDTO(courses);
  }
}
