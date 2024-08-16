package com.example.mergen_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.mergen_backend.entity.Email;
import com.example.mergen_backend.repository.EmailRepository;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, EmailRepository emailRepository) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
    }

    public void sendEmail(String to, String subject, String text, String status) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

        Email email = new Email();
        email.setRecipient(to);
        email.setSubject(subject);
        email.setContent(text);
        email.setStatus(status);
        emailRepository.save(email);
    }

    public List<Email> getSentEmails() {
        return (List<Email>) emailRepository.findAll();
    }

    public void deleteEmails(List<String> recipients) {
        recipients.forEach(recipient -> {
            List<Email> emails = emailRepository.findByRecipient(recipient);
            emails.forEach(email -> emailRepository.deleteById(email.getId()));
        });
    }

    public boolean isEmailSent(String recipient, String status) {
        return emailRepository.existsByRecipientAndStatus(recipient, status);
    }
}