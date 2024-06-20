package com.example.viaSoft.DTO;

import jakarta.validation.constraints.Size;

public class EmailAwsDTO {

    @Size(max = 45, message = "E-mail destinatário: Max: 45 caracteres")
    private String recipient;
    @Size(max = 60, message = "Nome destinatário: Max: 60 caracteres")
    private String recipientName;
    @Size(max = 45, message = "E-mail remetente: Max: 45 caracteres")
    private String sender;
    @Size(max = 120, message = "Assunto do e-mail. Max: 120 caracteres")
    private String subject;
    @Size(max = 256, message = "Conteúdo do e-mail. Max: 256 caracteres")
    private String content;

    public EmailAwsDTO( String recipient, String recipientName, String sender, String subject, String content) {
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
