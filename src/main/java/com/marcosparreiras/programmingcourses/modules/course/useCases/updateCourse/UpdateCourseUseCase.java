package com.marcosparreiras.programmingcourses.modules.course.useCases.updateCourse;

import com.marcosparreiras.programmingcourses.modules.course.exceptions.CourseAlreadyExistsError;
import com.marcosparreiras.programmingcourses.modules.course.exceptions.CourseNotFoundError;
import com.marcosparreiras.programmingcourses.modules.course.repositoires.CourseRepository;
import com.marcosparreiras.programmingcourses.modules.course.useCases.updateCourse.dtos.UpdateCourseRequestDTO;
import com.marcosparreiras.programmingcourses.modules.course.useCases.updateCourse.dtos.UpdateCourseResponseDTO;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseUseCase {

  @Autowired
  private CourseRepository courseRepository;

  public UpdateCourseResponseDTO execute(
    UpdateCourseRequestDTO updateCourseRequestDTO
  ) throws CourseNotFoundError, CourseAlreadyExistsError {
    var course =
      this.courseRepository.findById(
          UUID.fromString(updateCourseRequestDTO.id())
        )
        .orElseThrow(() -> {
          throw new CourseNotFoundError();
        });

    if (updateCourseRequestDTO.name() != null) {
      var courseAlreadyExists =
        this.courseRepository.findByName(updateCourseRequestDTO.name());
      if (
        courseAlreadyExists != null &&
        courseAlreadyExists.getId() != course.getId()
      ) {
        throw new CourseAlreadyExistsError();
      }
      course.setName(updateCourseRequestDTO.name());
    }

    if (updateCourseRequestDTO.category() != null) {
      course.setCategory(updateCourseRequestDTO.category());
    }

    this.courseRepository.save(course);

    return new UpdateCourseResponseDTO(course);
  }
}
