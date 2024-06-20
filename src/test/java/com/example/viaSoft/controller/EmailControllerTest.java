package com.example.viaSoft.controller;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class EmailControllerTest {
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailController emailController;

    @Test
    void sendEmail_succeeds() throws JsonProcessingException {
        EmailDTO emailDTO = new EmailDTO("teste@teste", "Teste Email padrao", "teste@testeenvio", "Envio teste", "ENVIO DE EMAIL TESTE");

        ResponseEntity<?> response = emailController.sendEmail(emailDTO);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void sendEmail_throwsIllegalArgumentException() throws JsonProcessingException {
        EmailDTO emailDTO = new EmailDTO("teste@testeteste@testeteste@testeteste@testeteste@testeteste@testeteste@testeteste@testeteste@testeteste@testeteste@teste", "Teste Email padrao", "teste@testeenvio", "Envio teste", "ENVIO DE EMAIL TESTE");

        assertThrows(IllegalArgumentException.class, () -> emailController.sendEmail(emailDTO));
    }
}
