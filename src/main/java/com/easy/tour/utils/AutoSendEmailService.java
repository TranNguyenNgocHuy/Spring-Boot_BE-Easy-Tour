package com.easy.tour.utils;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AutoSendEmailService {

    private final JavaMailSender mailSender;

    public AutoSendEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setupEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("khai.nguyenanh03@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void welcomeUserEmail(String email,
                                 String fistName,
                                 String lastName,
                                 String password) {
        String subject = "Welcome to EASY TOUR Company!";
        String body =
                "Dear " + fistName + " " + lastName + ",\n\n" +
                "Welcome to the EASY TOUR Company!\n\n" +
                "Below are your login credentials to access our systems:\n\n" +
                "Username: " + email + " \n" +
                "Password: " + password + "\n\n" +
                "Please make sure to keep this information secure and do not share it with anyone. If you have any questions or need assistance, feel free to reach out to our IT support team at easyTour@it.com.\n\n" +
                "Once again, welcome aboard! We look forward to achieving great things together.\n\n" +
                "Best regards,\n" +
                fistName + " " + lastName + " \n" +
                "EASY TOUR Company";

        setupEmail(email, subject, body);
    }

    public void forgotPasswordEmail(String email,
                                    String fistName,
                                    String lastName,
                                    String password
    ) {
        String subject = "Provision of New Password!";
        String body =
                "Dear " + fistName + " " + lastName + ",\n\n" +
                "We have received your request for a password reset\n\n" +
                "Below is your new password for your account: " + password + "\n\n" +
                "Best regards,\n" +
                fistName + " " + lastName + " \n" +
                "EASY TOUR Company";

        setupEmail(email, subject, body);
    }

}
