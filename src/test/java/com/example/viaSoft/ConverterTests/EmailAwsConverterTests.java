package com.example.viaSoft.ConverterTests;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.converter.EmailAwsConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailAwsConverterTests {

    @Test
    public void testConvert() {
        EmailDTO emailDTO = new EmailDTO("recipient@example.com", "Recipient Name", "sender@example.com", "Test Subject", "Test Content");
        EmailAwsConverter converter = new EmailAwsConverter();

        EmailAwsDTO emailAwsDTO = converter.convert(emailDTO);

        assertEquals(emailDTO.getRecipient(), emailAwsDTO.getRecipient());
        assertEquals(emailDTO.getRecipientName(), emailAwsDTO.getRecipientName());
        assertEquals(emailDTO.getSender(), emailAwsDTO.getSender());
        assertEquals(emailDTO.getSubject(), emailAwsDTO.getSubject());
        assertEquals(emailDTO.getContent(), emailAwsDTO.getContent());
    }
}
