package io.metadata.school.registration.api.common.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Course entity's DTO representation.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@Data
public class CourseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    private String description;
    @NotBlank
    private String mnemonic;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties(value = {"enrolledCourses", "createdAt"})
    private Set<StudentDTO> enrolledStudents;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    public CourseDTO() {
    }

    public CourseDTO(String description, String mnemonic) {
        this.description = description;
        this.mnemonic = mnemonic;
    }
}
