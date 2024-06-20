package com.example.viaSoft.ConverterTests;

import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.converter.EmailOciConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailOciConverterTests {

    @Test
    public void testConvert() {
        EmailDTO emailDTO = new EmailDTO("recipient@example.com", "Recipient Name", "sender@example.com", "Test Subject", "Test Content");
        EmailOciConverter converter = new EmailOciConverter();

        EmailOciDTO emailOciDTO = converter.convert(emailDTO);

        assertEquals(emailDTO.getRecipient(), emailOciDTO.getRecipientEmail());
        assertEquals(emailDTO.getRecipientName(), emailOciDTO.getRecipientName());
        assertEquals(emailDTO.getSender(), emailOciDTO.getSenderEmail());
        assertEquals(emailDTO.getSubject(), emailOciDTO.getSubject());
        assertEquals(emailDTO.getContent(), emailOciDTO.getBody());
    }
}
