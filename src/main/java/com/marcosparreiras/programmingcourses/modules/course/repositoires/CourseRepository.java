package com.marcosparreiras.programmingcourses.modules.course.repositoires;

import com.marcosparreiras.programmingcourses.modules.course.entities.Course;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, UUID> {
  Course findByName(String name);

  @Query(
    "SELECT c FROM courses c WHERE c.name LIKE %?1% OR c.category LIKE %?2%"
  )
  List<Course> findByNameOrCategoryContaining(String name, String category);
}
