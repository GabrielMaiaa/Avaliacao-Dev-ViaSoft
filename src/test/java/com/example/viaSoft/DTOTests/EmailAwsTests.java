package com.example.viaSoft.DTOTests;

import com.example.viaSoft.DTO.EmailAwsDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailAwsTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEmailAwsDTO() {
        EmailAwsDTO email = new EmailAwsDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        assertEquals("test@example.com", email.getRecipient());
        assertEquals("Test User", email.getRecipientName());
        assertEquals("sender@example.com", email.getSender());
        assertEquals("Test Subject", email.getSubject());
        assertEquals("Test Content", email.getContent());
    }

    @Test
    public void testSettersAndGetters() {
        EmailAwsDTO email = new EmailAwsDTO("", "", "", "", "");

        email.setRecipient("newrecipient@example.com");
        assertEquals("newrecipient@example.com", email.getRecipient());

        email.setRecipientName("New Recipient");
        assertEquals("New Recipient", email.getRecipientName());

        email.setSender("newsender@example.com");
        assertEquals("newsender@example.com", email.getSender());

        email.setSubject("New Subject");
        assertEquals("New Subject", email.getSubject());

        email.setContent("New Content");
        assertEquals("New Content", email.getContent());
    }

    @Test
    public void testRecipientSizeConstraint() {
        EmailAwsDTO email = new EmailAwsDTO("thisisaverylongemailaddressthatexceedsthemaximumallowedlength@example.com", "", "", "", "");
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("E-mail destinatário: Max: 45 caracteres")));
    }

    @Test
    public void testRecipientNameSizeConstraint() {
        EmailAwsDTO email = new EmailAwsDTO("", "This is a very long recipient name that exceeds the maximum allowed length for this field", "", "", "");
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Nome destinatário: Max: 60 caracteres")));
    }

    @Test
    public void testSenderSizeConstraint() {
        EmailAwsDTO email = new EmailAwsDTO("", "", "thisisaverylongemailaddressthatexceedsthemaximumallowedlength@example.com", "", "");
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("E-mail remetente: Max: 45 caracteres")));
    }

    @Test
    public void testSubjectSizeConstraint() {
        EmailAwsDTO email = new EmailAwsDTO("", "", "", "This is a very long subject that exceeds the maximum allowed length for this field and should be accepted now because we have increased the limit to 250 characters", "");
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Assunto do e-mail. Max: 120 caracteres")));
    }

    @Test
    public void testContentSizeConstraint() {
        EmailAwsDTO email = new EmailAwsDTO("", "", "", "", "This is a very long content that exceeds the maximum allowed length for this field. ".repeat(5));
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(email);
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Conteúdo do e-mail. Max: 256 caracteres")));
    }

}
