package com.example.viaSoft.ServicesTests;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.converter.EmailAwsConverter;
import com.example.viaSoft.converter.EmailOciConverter;
import com.example.viaSoft.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private Validator validator;

    @Mock
    private EmailAwsConverter emailAwsConverter;

    @Mock
    private EmailOciConverter emailOciConverter;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Logger logger;

    @Value("${mail.integracao}")
    private String mailIntegration;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        emailService = new EmailService();
        ReflectionTestUtils.setField(emailService, "mailIntegration", mailIntegration);
    }

    @Test
    public void testSendEmail_AWSIntegration() throws JsonProcessingException {
        EmailDTO emailDTO = new EmailDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");
        EmailAwsDTO emailAwsDTO = new EmailAwsDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        when(mailIntegration).thenReturn(EmailService.AWS_MAIL_INTEGRATION);
        when(emailAwsConverter.convert(emailDTO)).thenReturn(emailAwsDTO);

        emailService.sendEmail(emailDTO);

        verify(validator).validate(emailAwsDTO);
        verify(logger).info(objectMapper.writeValueAsString(emailAwsDTO));
    }

    @Test
    public void testSendEmail_OCIIntegration() throws JsonProcessingException {
        EmailDTO emailDTO = new EmailDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");
        EmailOciDTO emailOciDTO = new EmailOciDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        when(mailIntegration).thenReturn(EmailService.OCI_MAIL_INTEGRATION);
        when(emailOciConverter.convert(emailDTO)).thenReturn(emailOciDTO);

        emailService.sendEmail(emailDTO);

        verify(validator).validate(emailOciDTO);
        verify(logger).info(objectMapper.writeValueAsString(emailOciDTO));
    }

    @Test
    public void testSendEmail_UnsupportedIntegration() {
        EmailDTO emailDTO = new EmailDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        when(mailIntegration).thenReturn("unsupported");

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> emailService.sendEmail(emailDTO),
                "Expected sendEmail() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains(EmailService.NOT_SUPPORTED_INTEGRATION));
    }

}
