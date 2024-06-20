package com.example.viaSoft.ControllerTests;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.controller.EmailController;
import com.example.viaSoft.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void sendEmail_ShouldReturnNoContent_WhenEmailIsSent() throws Exception {
        EmailDTO emailDTO = new EmailDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        mockMvc.perform(post("/emails/send-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void sendEmail_ShouldReturnBadRequest_WhenServiceThrowsException() throws Exception {
        EmailDTO emailDTO = new EmailDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        Mockito.doThrow(new RuntimeException()).when(emailService).sendEmail(Mockito.any(EmailDTO.class));

        mockMvc.perform(post("/emails/send-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDTO)))
                .andExpect(status().isBadRequest());
    }

}
