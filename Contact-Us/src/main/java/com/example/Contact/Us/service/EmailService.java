package com.example.Contact.Us.service;

import com.example.Contact.Us.dto.ContactFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String siteOwnerEmail = "owner@example.com"; // Replace with your own

    public void sendEmails(ContactFormRequest request) {
        sendToOwner(request);
        sendConfirmationToUser(request);
    }

    private void sendToOwner(ContactFormRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(siteOwnerEmail);
        message.setSubject("New Contact Form Submission: " + request.getSubject());
        message.setText("Name: " + request.getName() + "\n"
                + "Email: " + request.getEmail() + "\n\n"
                + request.getMessage());
        mailSender.send(message);
    }

    private void sendConfirmationToUser(ContactFormRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("Thank you for contacting us!");
        message.setText("Hi " + request.getName() + ",\n\n"
                + "We’ve received your message:\n\n"
                + "\"" + request.getMessage() + "\"\n\n"
                + "We’ll respond shortly.\n\nBest,\nTeam");
        mailSender.send(message);
    }
}
