package com.marcosparreiras.programmingcourses.modules.course.entities;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class Course {

  private UUID id;
  private String name;
  private String category;
  private boolean isActive;
  private Date createdAt;
  private Date updatedAt;
}
