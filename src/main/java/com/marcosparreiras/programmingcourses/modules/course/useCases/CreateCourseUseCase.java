package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.modules.course.dtos.CreateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import com.marcosparreiras.programmingcourses.modules.exceptions.CourseAlreadyExistsError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public Course execute(CreateCourseRequestDTO createCourseRequestDTO)
    throws CourseAlreadyExistsError {
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

    return newCourse;
  }
}
