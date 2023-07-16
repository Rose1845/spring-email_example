package com.example.springemail.controller;

import com.example.springemail.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;


@RestController
public class EmailController {

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
           MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);


            helper.setFrom("odhiamborose466@gmail.com");
            helper.setTo("roseodhiambo466@gmail.com");
            helper.setSubject(emailRequest.getSubject());
            helper.setText("<html><h1>Hello Email Attachment</h1></html>");

            String content = String.format("Dear Rose, a user by the name: %s, email: %s, message: %s, and subject: %s contacted you.",
                    emailRequest.getName(), emailRequest.getEmail(), emailRequest.getMessage(), emailRequest.getSubject());

            helper.setText(content, true);
            if(emailRequest.getFile() == null){
                MultipartFile attachment = emailRequest.getFile();
                ByteArrayResource atttachmentResource = new ByteArrayResource(attachment.getBytes());
                helper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()),atttachmentResource);
            }

            mailSender.send(message);

            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}