package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.modules.course.dtos.FetchCoursesRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FetchCoursesUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public List<Course> execute(FetchCoursesRequestDTO fetchCoursesRequestDTO) {
    if (
      fetchCoursesRequestDTO.name() != null ||
      fetchCoursesRequestDTO.category() != null
    ) {
      return this.courseRepository.findByNameOrCategoryContaining(
          fetchCoursesRequestDTO.name(),
          fetchCoursesRequestDTO.category()
        );
    }
    return this.courseRepository.findAll();
  }
}
