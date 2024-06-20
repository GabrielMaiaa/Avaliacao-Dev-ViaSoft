package com.example.viaSoft.DTOTests;

import com.example.viaSoft.DTO.EmailAwsDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailAwsTests {

    @Test
    void testEmailAwsDTOConstructor() {
        String recipient = "recipient@example.com";
        String recipientName = "Recipient Name";
        String sender = "sender@example.com";
        String subject = "Subject";
        String content = "Content";

        EmailAwsDTO emailAwsDTO = new EmailAwsDTO(recipient, recipientName, sender, subject, content);

        assertEquals(recipient, emailAwsDTO.getRecipient());
        assertEquals(recipientName, emailAwsDTO.getRecipientName());
        assertEquals(sender, emailAwsDTO.getSender());
        assertEquals(subject, emailAwsDTO.getSubject());
        assertEquals(content, emailAwsDTO.getContent());
    }

    @Test
    void testSettersAndGetters() {
        EmailAwsDTO emailAwsDTO = new EmailAwsDTO("", "", "", "", "");

        String recipient = "recipient@example.com";
        emailAwsDTO.setRecipient(recipient);
        assertEquals(recipient, emailAwsDTO.getRecipient());

        String recipientName = "Recipient Name";
        emailAwsDTO.setRecipientName(recipientName);
        assertEquals(recipientName, emailAwsDTO.getRecipientName());

        String sender = "sender@example.com";
        emailAwsDTO.setSender(sender);
        assertEquals(sender, emailAwsDTO.getSender());

        String subject = "Subject";
        emailAwsDTO.setSubject(subject);
        assertEquals(subject, emailAwsDTO.getSubject());

        String content = "Content";
        emailAwsDTO.setContent(content);
        assertEquals(content, emailAwsDTO.getContent());
    }
}
