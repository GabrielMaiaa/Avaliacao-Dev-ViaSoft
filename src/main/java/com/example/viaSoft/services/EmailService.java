package com.example.viaSoft.services;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.domain.Email;
import com.example.viaSoft.repositories.EmailRepository;
import com.example.viaSoft.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public Email find(Integer codigo) {
        Optional<Email> objeto = emailRepository.findById(codigo);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                "Email não enviado! Id: " + codigo + "."));
    }

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Email insert(EmailDTO emailDTO) {
        Email email = new Email(null,
                emailDTO.getRecipient(),
                emailDTO.getRecipientName(),
                emailDTO.getSender(),
                emailDTO.getSubject(),
                emailDTO.getContent());
        return emailRepository.save(email);
    }

    public Email insertAws(EmailAwsDTO emailAwsDTO) {
        Email email = new Email(null,
                emailAwsDTO.getRecipient(),
                emailAwsDTO.getRecipientName(),
                emailAwsDTO.getSender(),
                emailAwsDTO.getSubject(),
                emailAwsDTO.getContent());
        return emailRepository.save(email);
    }

    public Email insertOci(EmailOciDTO emailOciDTO) {
        Email email = new Email(null,
                emailOciDTO.getRecipientEmail(),
                emailOciDTO.getRecipientName(),
                emailOciDTO.getSenderEmail(),
                emailOciDTO.getSubject(),
                emailOciDTO.getBody());
        return emailRepository.save(email);
    }

}
