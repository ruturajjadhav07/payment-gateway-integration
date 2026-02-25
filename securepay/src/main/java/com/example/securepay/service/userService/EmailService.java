package com.example.securepay.service.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    // Send welcome message
    public void sendWelcome(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Welcome to Express Delivery 🚚");

        message.setText(
                "Dear " + name + ",\n\n" +
                        "Welcome to Express Delivery!\n\n" +
                        "Your account has been successfully created. " +
                        "You can now browse products, add items to your cart, and enjoy fast and secure delivery.\n\n" +
                        "We’re committed to delivering your orders quickly and safely right to your doorstep.\n\n" +
                        "If you have any questions, our support team is always here to help.\n\n" +
                        "Best regards,\n" +
                        "The Express Delivery Team"
        );

        javaMailSender.send(message);
    }
}