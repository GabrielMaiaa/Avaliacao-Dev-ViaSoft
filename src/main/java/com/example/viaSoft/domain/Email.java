package com.example.viaSoft.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoEmail;
    private String recipient;
    private String recipientName;
    private String sender;
    private String subject;
    private String content;

    public Email() {
    }

    public Email(Integer codigoEmail, String recipient, String recipientName, String sender, String subject, String content) {
        this.codigoEmail = codigoEmail;
        this.recipient = recipient;
        this.recipientName = recipientName;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public Integer getCodigoEmail() {
        return codigoEmail;
    }

    public void setCodigoEmail(Integer codigoEmail) {
        this.codigoEmail = codigoEmail;
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
