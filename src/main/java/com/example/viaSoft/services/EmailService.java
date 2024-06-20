package com.example.viaSoft.services;

import com.example.viaSoft.DTO.EmailAwsDTO;
import com.example.viaSoft.DTO.EmailDTO;
import com.example.viaSoft.DTO.EmailOciDTO;
import com.example.viaSoft.converter.EmailAwsConverter;
import com.example.viaSoft.converter.EmailOciConverter;
import com.example.viaSoft.validator.EmailAwsValidator;
import com.example.viaSoft.validator.EmailOciValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EmailService {

    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    public static final String AWS_MAIL_INTEGRATION = "aws";
    public static final String OCI_MAIL_INTEGRATION = "oci";
    public static final String NOT_SUPPORTED_INTEGRATION = "Integração não suportada: ";

    @Value("${mail.integracao}")
    private String mailIntegration;
    @Autowired
    private EmailAwsConverter emailAwsConverter;
    @Autowired
    private EmailOciConverter emailOciConverter;
    @Autowired
    private EmailOciValidator emailOciValidator;
    @Autowired
    private EmailAwsValidator emailAwsValidator;

    @Autowired
    private ObjectMapper objectMapper;


    public void sendEmail(EmailDTO emailDTO) throws JsonProcessingException {
        if (mailIntegration.equalsIgnoreCase(AWS_MAIL_INTEGRATION)) {
            EmailAwsDTO emailAwsDTO = emailAwsConverter.convert(emailDTO);
            processEmailAws(emailAwsDTO);
        } else if (mailIntegration.equalsIgnoreCase(OCI_MAIL_INTEGRATION)) {
            EmailOciDTO emailOciDTO = emailOciConverter.convert(emailDTO);
            processEmailOci(emailOciDTO);
        } else {
            throw new IllegalArgumentException(NOT_SUPPORTED_INTEGRATION + mailIntegration);
        }
    }

    private void processEmailAws(EmailAwsDTO email) throws JsonProcessingException {
        emailAwsValidator.validate(email);
        logger.info(objectMapper.writeValueAsString(email));
    }
    private void processEmailOci(EmailOciDTO email) throws JsonProcessingException {
        emailOciValidator.validate(email);
        logger.info(objectMapper.writeValueAsString(email));
    }

    // Método para definir a integração do e-mail
    public void setMailIntegration(String integration) {
        this.mailIntegration = integration;
    }

}
