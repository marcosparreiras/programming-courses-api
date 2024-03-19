package com.marcosparreiras.programmingcourses.modules.course.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Length(min = 3, message = "Name is a required field")
  private String name;

  @Length(min = 3, message = "Category is a required field")
  private String category;

  private boolean isActive;

  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;
}
