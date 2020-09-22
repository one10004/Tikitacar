package com.bigdata.tikitacar.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Async
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();

//            MimeMessage mimeMessage = emailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text,true);
//
//            emailSender.send(mimeMessage);
        msg.setFrom("Tikitacar");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol("smtp");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("tikitacar106@gmail.com");
        mailSender.setPassword("ssafy@106");

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(prop);
        mailSender.send(msg);

    }
}
