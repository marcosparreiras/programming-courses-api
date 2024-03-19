package com.marcosparreiras.programmingcourses.modules.course.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateCourseRequestDTO {

  @Length(
    min = 3,
    message = "Name is a required field and should have at least 3 characters"
  )
  private String name;

  @Length(
    min = 3,
    message = "Category is a required field and should have at least 3 characters"
  )
  private String category;
}
