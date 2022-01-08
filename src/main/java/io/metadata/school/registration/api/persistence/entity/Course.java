package io.metadata.school.registration.api.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representation of the course table.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@Data
@Entity
@Table
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String mnemonic;

    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> enrolledStudents;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(description, course.description) && Objects.equals(mnemonic, course.mnemonic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, mnemonic);
    }
}
