package io.metadata.school.registration.api.persistence.repository;

import io.metadata.school.registration.api.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Handles CRUD for the Course entity.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByMnemonic(String mnemonic);
}
