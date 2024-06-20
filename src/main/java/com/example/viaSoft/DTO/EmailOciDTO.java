package com.example.viaSoft.DTO;

import jakarta.validation.constraints.Size;

public class EmailOciDTO {

    @Size(max = 40, message = "E-mail destinatário: Max: 40 caracteres")
    private String recipientEmail;
    @Size(max = 50, message = "Nome destinário. Max: 50 caracteres")
    private String recipientName;
    @Size(max = 40, message = "E-mail remetente. Max: 40 caracteres")
    private String senderEmail;
    @Size(max = 100, message = "Assunto do e-mail. Max: 100 caracteres")
    private String subject;
    @Size(max = 45, message = "Conteúdo do e-mail. Max: 250 caracteres")
    private String body;

    public EmailOciDTO(String recipientEmail, String recipientName, String senderEmail, String subject, String body) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.body = body;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
