package com.example.viaSoft.ServicesTests;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.converter.EmailAwsConverter;
import com.example.viaSoft.converter.EmailOciConverter;
import com.example.viaSoft.services.EmailService;
import com.example.viaSoft.validator.EmailAwsValidator;
import com.example.viaSoft.validator.EmailOciValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @InjectMocks
    EmailService emailService;

    @Mock
    EmailAwsConverter emailAwsConverter;

    @Mock
    EmailOciConverter emailOciConverter;

    @Mock
    EmailOciValidator emailOciValidator;

    @Mock
    EmailAwsValidator emailAwsValidator;

    @Mock
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendEmailWithAwsIntegration() throws JsonProcessingException {
        // Given
        EmailDTO emailDTO = new EmailDTO();
        EmailAwsDTO emailAwsDTO = new EmailAwsDTO();
        emailService.setMailIntegration(EmailService.AWS_MAIL_INTEGRATION); // Definindo integração AWS
        when(emailAwsConverter.convert(emailDTO)).thenReturn(emailAwsDTO);

        // When
        emailService.sendEmail(emailDTO);

        // Then
        verify(emailAwsValidator, times(1)).validate(emailAwsDTO);
    }

    @Test
    void testSendEmailWithOciIntegration() throws JsonProcessingException {
        // Given
        EmailDTO emailDTO = new EmailDTO();
        EmailOciDTO emailOciDTO = new EmailOciDTO();
        emailService.setMailIntegration(EmailService.OCI_MAIL_INTEGRATION); // Definindo integração OCI
        when(emailOciConverter.convert(emailDTO)).thenReturn(emailOciDTO);

        // When
        emailService.sendEmail(emailDTO);

        // Then
        verify(emailOciValidator, times(1)).validate(emailOciDTO);
    }

    @Test
    void testSendEmailWithUnsupportedIntegration() {
        // Given
        EmailDTO emailDTO = new EmailDTO();
        emailService.setMailIntegration("unsupported"); // Definindo integração não suportada

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> emailService.sendEmail(emailDTO));
    }

}
