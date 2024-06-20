package com.example.viaSoft.converter;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import org.springframework.stereotype.Component;

@Component
public class EmailAwsConverter implements EmailConverter<EmailAwsDTO> {

    @Override
    public EmailAwsDTO convert(EmailDTO emailDTO){
        return new EmailAwsDTO(emailDTO.getRecipient(), emailDTO.getRecipientName(), emailDTO.getSender(), emailDTO.getSubject(), emailDTO.getContent());
    }
}
