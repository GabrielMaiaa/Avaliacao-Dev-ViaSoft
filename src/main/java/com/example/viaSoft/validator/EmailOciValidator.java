package com.example.viaSoft.validator;

import com.example.viaSoft.DTO.EmailOciDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

public class EmailOciValidator implements EmailValidator<EmailOciDTO> {

    private final Validator validator;

    public EmailOciValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(EmailOciDTO emailOciDTO) {
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(emailOciDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<EmailOciDTO> violation : violations) {
                sb.append(violation.getMessage()).append(", ");
            }
            throw new IllegalArgumentException("Validation failed: " + sb.toString());
        }
    }
}
