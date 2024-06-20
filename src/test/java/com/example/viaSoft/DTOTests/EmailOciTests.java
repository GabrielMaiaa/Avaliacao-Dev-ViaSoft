package com.example.viaSoft.DTOTests;

import com.example.viaSoft.DTO.EmailOciDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailOciTests {

    @Test
    void testEmailOciDTOConstructor() {
        String recipientEmail = "recipient@example.com";
        String recipientName = "Recipient Name";
        String senderEmail = "sender@example.com";
        String subject = "Subject";
        String body = "Body";

        EmailOciDTO emailOciDTO = new EmailOciDTO(recipientEmail, recipientName, senderEmail, subject, body);

        assertEquals(recipientEmail, emailOciDTO.getRecipientEmail());
        assertEquals(recipientName, emailOciDTO.getRecipientName());
        assertEquals(senderEmail, emailOciDTO.getSenderEmail());
        assertEquals(subject, emailOciDTO.getSubject());
        assertEquals(body, emailOciDTO.getBody());
    }

    @Test
    void testSettersAndGetters() {
        EmailOciDTO emailOciDTO = new EmailOciDTO("", "", "", "", "");

        String recipientEmail = "recipient@example.com";
        emailOciDTO.setRecipientEmail(recipientEmail);
        assertEquals(recipientEmail, emailOciDTO.getRecipientEmail());

        String recipientName = "Recipient Name";
        emailOciDTO.setRecipientName(recipientName);
        assertEquals(recipientName, emailOciDTO.getRecipientName());

        String senderEmail = "sender@example.com";
        emailOciDTO.setSenderEmail(senderEmail);
        assertEquals(senderEmail, emailOciDTO.getSenderEmail());

        String subject = "Subject";
        emailOciDTO.setSubject(subject);
        assertEquals(subject, emailOciDTO.getSubject());

        String body = "Body";
        emailOciDTO.setBody(body);
        assertEquals(body, emailOciDTO.getBody());
    }

}
