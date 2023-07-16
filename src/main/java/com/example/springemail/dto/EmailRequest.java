package com.example.springemail.dto;


import org.springframework.web.multipart.MultipartFile;

public class EmailRequest {
    private String email;
    private String subject ;
    private String message;
    private String name;
    private MultipartFile file;

    public EmailRequest(String email, String subject, String message, String name, MultipartFile file) {
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.name = name;
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
