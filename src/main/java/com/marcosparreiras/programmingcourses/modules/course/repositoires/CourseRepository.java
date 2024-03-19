package com.marcosparreiras.programmingcourses.modules.course.repositoires;

import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, UUID> {}
