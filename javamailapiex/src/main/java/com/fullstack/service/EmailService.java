package com.fullstack.service;

import com.fullstack.model.Emailmodel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("$(spring.mail.username)")
    private String fromEmail;

    public void sendEmail(Emailmodel emailmodel) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(emailmodel.getToEmail());
            mimeMessageHelper.setTo(emailmodel.getCcEmail());
            mimeMessageHelper.setSubject(emailmodel.getEmailSubject());
            mimeMessageHelper.setText(emailmodel.getEmailBody());
            FileSystemResource fileSystemResource = new FileSystemResource(new File(emailmodel.getEmailAttachment()));

            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
