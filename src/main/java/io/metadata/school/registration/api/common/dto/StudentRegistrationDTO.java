package io.metadata.school.registration.api.common.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Student registration DTO representation.
 *
 * @author Ernesto ACosta
 * @since 1/7/2022
 */
@Data
public class StudentRegistrationDTO {

    @NotNull
    private Long studentId;
    @NotBlank
    private String mnemonic;

    public StudentRegistrationDTO() {
    }

    public StudentRegistrationDTO(Long studentId, String mnemonic) {
        this.studentId = studentId;
        this.mnemonic = mnemonic;
    }
}
