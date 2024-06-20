package com.example.viaSoft.ControllerTests;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.controller.EmailController;
import com.example.viaSoft.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @InjectMocks
    EmailController emailController;

    @Mock
    EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendEmail() throws JsonProcessingException {
        // Given
        EmailDTO emailDTO = new EmailDTO();

        // When
        ResponseEntity<?> responseEntity = emailController.sendEmail(emailDTO);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(emailService, times(1)).sendEmail(emailDTO);
    }

}
