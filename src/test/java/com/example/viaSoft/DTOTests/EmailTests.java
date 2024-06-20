package com.example.viaSoft.DTOTests;

import com.example.viaSoft.DTO.EmailDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailTests {

    @Test
    public void testEmailDTO() {
        EmailDTO email = new EmailDTO("test@example.com", "Test User", "sender@example.com", "Test Subject", "Test Content");

        assertEquals("test@example.com", email.getRecipient());
        assertEquals("Test User", email.getRecipientName());
        assertEquals("sender@example.com", email.getSender());
        assertEquals("Test Subject", email.getSubject());
        assertEquals("Test Content", email.getContent());
    }

    @Test
    public void testSettersAndGetters() {
        EmailDTO email = new EmailDTO("", "", "", "", "");

        email.setRecipient("newrecipient@example.com");
        assertEquals("newrecipient@example.com", email.getRecipient());

        email.setRecipientName("New Recipient");
        assertEquals("New Recipient", email.getRecipientName());

        email.setSender("newsender@example.com");
        assertEquals("newsender@example.com", email.getSender());

        email.setSubject("New Subject");
        assertEquals("New Subject", email.getSubject());

        email.setContent("New Content");
        assertEquals("New Content", email.getContent());
    }
}
