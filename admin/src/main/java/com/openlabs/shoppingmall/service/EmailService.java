package com.openlabs.shoppingmall.service;

import com.openlabs.shoppingmall.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(EmailMessage emailMessage) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); //메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); //메일 제목
            mimeMessageHelper.setText("test");

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("fail sending email", e);
        }

    }
}
