package com.example.viaSoft.controller;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDTO emailDTO) throws JsonProcessingException {
        emailService.sendEmail(emailDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
