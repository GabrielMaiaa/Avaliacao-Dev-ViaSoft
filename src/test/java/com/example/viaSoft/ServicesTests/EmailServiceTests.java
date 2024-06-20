package com.example.viaSoft.ServicesTests;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.domain.Email;
import com.example.viaSoft.repositories.EmailRepository;
import com.example.viaSoft.services.EmailService;
import com.example.viaSoft.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private EmailRepository emailRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFind() {
        Email email = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailRepository.findById(1)).thenReturn(Optional.of(email));

        Email foundEmail = emailService.find(1);

        assertNotNull(foundEmail);
        assertEquals(1, foundEmail.getCodigoEmail());
        assertEquals("recipient@example.com", foundEmail.getRecipient());
    }

    @Test
    void testFindNotFound() {
        when(emailRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            emailService.find(1);
        });

        String expectedMessage = "Email não enviado! Id: 1.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindAll() {
        Email email1 = new Email(1, "recipient1@example.com", "Recipient Name 1", "sender1@example.com", "Subject 1", "Content 1");
        Email email2 = new Email(2, "recipient2@example.com", "Recipient Name 2", "sender2@example.com", "Subject 2", "Content 2");
        List<Email> emails = Arrays.asList(email1, email2);

        when(emailRepository.findAll()).thenReturn(emails);

        List<Email> foundEmails = emailService.findAll();

        assertNotNull(foundEmails);
        assertEquals(2, foundEmails.size());
    }

    @Test
    void testInsertEmailDTO() {
        Email email = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailRepository.save(email)).thenReturn(email);

        assertEquals(1, email.getCodigoEmail());
    }

    @Test
    void testInsertEmailAwsDTO() {
        Email email = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailRepository.save(email)).thenReturn(email);

        assertEquals(1, email.getCodigoEmail());
    }

    @Test
    void testInsertEmailOciDTO() {
        Email email = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailRepository.save(email)).thenReturn(email);

        assertEquals(1, email.getCodigoEmail());
    }

}
