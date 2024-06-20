package com.example.viaSoft.DTOTests;

import com.example.viaSoft.domain.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmailTests {

    @Test
    void testEmailConstructor() {
        Integer codigoEmail = 1;
        String recipient = "recipient@example.com";
        String recipientName = "Recipient Name";
        String sender = "sender@example.com";
        String subject = "Subject";
        String content = "Content";

        Email email = new Email(codigoEmail, recipient, recipientName, sender, subject, content);

        assertEquals(codigoEmail, email.getCodigoEmail());
        assertEquals(recipient, email.getRecipient());
        assertEquals(recipientName, email.getRecipientName());
        assertEquals(sender, email.getSender());
        assertEquals(subject, email.getSubject());
        assertEquals(content, email.getContent());
    }

    @Test
    void testSettersAndGetters() {
        Email email = new Email();

        Integer codigoEmail = 1;
        email.setCodigoEmail(codigoEmail);
        assertEquals(codigoEmail, email.getCodigoEmail());

        String recipient = "recipient@example.com";
        email.setRecipient(recipient);
        assertEquals(recipient, email.getRecipient());

        String recipientName = "Recipient Name";
        email.setRecipientName(recipientName);
        assertEquals(recipientName, email.getRecipientName());

        String sender = "sender@example.com";
        email.setSender(sender);
        assertEquals(sender, email.getSender());

        String subject = "Subject";
        email.setSubject(subject);
        assertEquals(subject, email.getSubject());

        String content = "Content";
        email.setContent(content);
        assertEquals(content, email.getContent());
    }

    @Test
    void testDefaultConstructor() {
        Email email = new Email();
        assertNull(email.getCodigoEmail());
        assertNull(email.getRecipient());
        assertNull(email.getRecipientName());
        assertNull(email.getSender());
        assertNull(email.getSubject());
        assertNull(email.getContent());
    }
}
