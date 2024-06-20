package com.example.viaSoft.converter;

import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import org.springframework.stereotype.Component;

@Component
public class EmailOciConverter implements EmailConverter<EmailOciDTO> {

    @Override
    public EmailOciDTO convert(EmailDTO emailDTO){
        return new EmailOciDTO(emailDTO.getRecipient(), emailDTO.getRecipientName(), emailDTO.getSender(), emailDTO.getSubject(), emailDTO.getContent());
    }
}
