package com.example.viaSoft.resources;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.domain.Email;
import com.example.viaSoft.services.EmailService;
import com.example.viaSoft.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/{codigo}")
    public ResponseEntity find(@PathVariable Integer codigo) {
        try {
            Email objeto = emailService.find(codigo);
            return ResponseEntity.ok().body(objeto);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ObjectNotFound --- > " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Email>> findAll() {
        try {
            List<Email> listEmails = emailService.findAll();
            return ResponseEntity.ok().body(listEmails);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping
    public ResponseEntity<Email> insert(@RequestBody EmailDTO emailDTO) {
        try {
            Email newEmail = emailService.insert(emailDTO);
            return new ResponseEntity<Email>(newEmail, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping(value = "/salvar-aws")
    public ResponseEntity<Email> insert(@RequestBody EmailAwsDTO emailAwsDTO) {
        try {
            Email newEmail = emailService.insertAws(emailAwsDTO);
            return new ResponseEntity<Email>(newEmail, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping(value = "/salvar-oci")
    public ResponseEntity<Email> insert(@RequestBody EmailOciDTO emailOciDTO) {
        try {
            Email newEmail = emailService.insertOci(emailOciDTO);
            return new ResponseEntity<Email>(newEmail, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

}
