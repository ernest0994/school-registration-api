package io.metadata.school.registration.api.persistence.repository;

import io.metadata.school.registration.api.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Handles CRUD for the Student entity.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
