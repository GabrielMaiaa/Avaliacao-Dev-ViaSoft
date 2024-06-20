package com.example.viaSoft.DTOTests;

import com.example.viaSoft.DTO.EmailOciDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailOciTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEmailOciDTO() {
        EmailOciDTO email = new EmailOciDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        assertEquals("test@example.com", email.getRecipientEmail());
        assertEquals("Test User", email.getRecipientName());
        assertEquals("sender@example.com", email.getSenderEmail());
        assertEquals("Test Subject", email.getSubject());
        assertEquals("Test Content", email.getBody());
    }

    @Test
    public void testSettersAndGetters() {
        EmailOciDTO email = new EmailOciDTO("", "", "", "", "");

        email.setRecipientEmail("newrecipient@example.com");
        assertEquals("newrecipient@example.com", email.getRecipientEmail());

        email.setRecipientName("New Recipient");
        assertEquals("New Recipient", email.getRecipientName());

        email.setSenderEmail("newsender@example.com");
        assertEquals("newsender@example.com", email.getSenderEmail());

        email.setSubject("New Subject");
        assertEquals("New Subject", email.getSubject());

        email.setBody("New Content");
        assertEquals("New Content", email.getBody());
    }

    @Test
    public void testRecipientEmailSizeConstraint() {
        EmailOciDTO email = new EmailOciDTO("thisisaverylongemailaddressthatexceedsthemaximumallowedlength@example.com", "", "", "", "");
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("E-mail destinatário: Max: 40 caracteres")));
    }

    @Test
    public void testRecipientNameSizeConstraint() {
        EmailOciDTO email = new EmailOciDTO("", "This is a very long recipient name that exceeds the maximum allowed length for this field", "", "", "");
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nome destinário. Max: 50 caracteres")));
    }

    @Test
    public void testSenderEmailSizeConstraint() {
        EmailOciDTO email = new EmailOciDTO("", "", "thisisaverylongemailaddressthatexceedsthemaximumallowedlength@example.com", "", "");
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("E-mail remetente. Max: 40 caracteres")));
    }

    @Test
    public void testSubjectSizeConstraint() {
        EmailOciDTO email = new EmailOciDTO("", "", "", "This is a very long subject that exceeds the maximum allowed length for this field and should be accepted now", "");
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Assunto do e-mail. Max: 100 caracteres")));
    }

    @Test
    public void testBodySizeConstraint() {
        EmailOciDTO email = new EmailOciDTO("", "", "", "", "This is a very long content that exceeds the maximum allowed length for this field. ".repeat(5));
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Conteúdo do e-mail. Max: 250 caracteres")));
    }

}
