package com.marcosparreiras.programmingcourses.modules.course.useCases;

import com.marcosparreiras.programmingcourses.exceptions.CourseAlreadyExistsError;
import com.marcosparreiras.programmingcourses.modules.course.dtos.CreateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public Course execute(CreateCourseRequestDTO createCourseRequestDTO)
    throws CourseAlreadyExistsError {
    var courseAlreadyExists =
      this.courseRepository.findByName(createCourseRequestDTO.getName());

    if (courseAlreadyExists != null) {
      throw new CourseAlreadyExistsError();
    }

    var newCourse = Course
      .builder()
      .name(createCourseRequestDTO.getName())
      .category(createCourseRequestDTO.getCategory())
      .isActive(false)
      .build();

    this.courseRepository.save(newCourse);

    return newCourse;
  }
}
