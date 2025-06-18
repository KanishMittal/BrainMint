package com.example.Contact.Us.controller;

import com.example.Contact.Us.dto.ContactFormRequest;
import com.example.Contact.Us.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> handleContactForm(@RequestBody ContactFormRequest request) {
        emailService.sendEmails(request);
        return ResponseEntity.ok("Message sent successfully.");
    }
}