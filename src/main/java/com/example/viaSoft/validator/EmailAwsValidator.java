package com.example.viaSoft.validator;

import com.example.viaSoft.DTO.EmailAwsDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EmailAwsValidator implements EmailValidator<EmailAwsDTO> {

    private final Validator validator;

    public EmailAwsValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void validate(EmailAwsDTO emailAwsDTO) {
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(emailAwsDTO);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<EmailAwsDTO> violation : violations) {
                sb.append(violation.getMessage()).append(", ");
            }
            throw new IllegalArgumentException("Validation failed: " + sb.toString());
        }
    }
}
