package io.metadata.school.registration.api.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentRegistrationDTO {

    @NotNull
    private Long studentId;
    @NotBlank
    private String mnemonic;
}
