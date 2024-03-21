package com.marcosparreiras.programmingcourses.modules.course.useCases.createCourse;

import com.marcosparreiras.programmingcourses.exceptions.CourseAlreadyExistsError;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import com.marcosparreiras.programmingcourses.modules.course.useCases.createCourse.dtos.CreateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.createCourse.dtos.CreateCourseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public CreateCourseResponseDTO execute(
    CreateCourseRequestDTO createCourseRequestDTO
  ) throws CourseAlreadyExistsError {
    var courseAlreadyExists =
      this.courseRepository.findByName(createCourseRequestDTO.name());

    if (courseAlreadyExists != null) {
      throw new CourseAlreadyExistsError();
    }

    var newCourse = Course
      .builder()
      .name(createCourseRequestDTO.name())
      .category(createCourseRequestDTO.category())
      .isActive(false)
      .build();

    this.courseRepository.save(newCourse);

    return new CreateCourseResponseDTO(newCourse);
  }
}
