package com.example.viaSoft.controller;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDTO emailDTO) throws JsonProcessingException {
        try {
            emailService.sendEmail(emailDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

}
