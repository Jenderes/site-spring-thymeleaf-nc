package com.simple.demo.service.impl;

import com.simple.demo.model.MessageDto;
import com.simple.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void SendMessage(MessageDto messageDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("example@mail.ru");
        message.setTo(messageDto.getEmail());
        message.setSubject(messageDto.getTheme());
        message.setText(messageDto.getTextMessage());
        emailSender.send(message);
    }
}
