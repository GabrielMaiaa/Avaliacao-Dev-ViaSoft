package com.example.viaSoft.ResourcesTests;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.domain.Email;
import com.example.viaSoft.resources.EmailResource;
import com.example.viaSoft.services.EmailService;
import com.example.viaSoft.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class EmailResourceTest {

    private MockMvc mockMvc;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailResource emailResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emailResource).build();
    }

    @Test
    void testFind() throws Exception {
        Email email = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailService.find(1)).thenReturn(email);

        mockMvc.perform(get("/emails/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigoEmail").value(1))
                .andExpect(jsonPath("$.recipient").value("recipient@example.com"));
    }

    @Test
    void testFindNotFound() throws Exception {
        when(emailService.find(1)).thenThrow(new ObjectNotFoundException("Email not found"));

        mockMvc.perform(get("/emails/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ObjectNotFound --- > Email not found"));
    }

    @Test
    void testFindAll() throws Exception {
        Email email1 = new Email(1, "recipient1@example.com", "Recipient Name 1", "sender1@example.com", "Subject 1", "Content 1");
        Email email2 = new Email(2, "recipient2@example.com", "Recipient Name 2", "sender2@example.com", "Subject 2", "Content 2");
        List<Email> emails = Arrays.asList(email1, email2);

        when(emailService.findAll()).thenReturn(emails);

        mockMvc.perform(get("/emails"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].codigoEmail").value(1))
                .andExpect(jsonPath("$[1].codigoEmail").value(2));
    }

    @Test
    void testInsert() throws Exception {
        EmailDTO emailDTO = new EmailDTO("recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");
        Email newEmail = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailService.insert(emailDTO)).thenReturn(newEmail);

        mockMvc.perform(post("/emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipient\":\"recipient@example.com\", \"recipientName\":\"Recipient Name\", \"sender\":\"sender@example.com\", \"subject\":\"Subject\", \"content\":\"Content\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testInsertAws() throws Exception {
        EmailAwsDTO emailAwsDTO = new EmailAwsDTO( "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");
        Email newEmail = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailService.insertAws(emailAwsDTO)).thenReturn(newEmail);

        mockMvc.perform(post("/emails/salvar-aws")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipient\":\"recipient@example.com\", \"recipientName\":\"Recipient Name\", \"sender\":\"sender@example.com\", \"subject\":\"Subject\", \"content\":\"Content\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testInsertOci() throws Exception {
        EmailOciDTO emailOciDTO = new EmailOciDTO( "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");
        Email newEmail = new Email(1, "recipient@example.com", "Recipient Name", "sender@example.com", "Subject", "Content");

        when(emailService.insertOci(emailOciDTO)).thenReturn(newEmail);

        mockMvc.perform(post("/emails/salvar-oci")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipientEmail\":\"recipient@example.com\", \"recipientName\":\"Recipient Name\", \"senderEmail\":\"sender@example.com\", \"subject\":\"Subject\", \"body\":\"Content\"}"))
                .andExpect(status().isCreated());
    }

}
