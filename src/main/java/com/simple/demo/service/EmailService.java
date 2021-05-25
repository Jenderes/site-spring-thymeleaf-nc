package com.simple.demo.service;

import com.simple.demo.model.MessageDto;

public interface EmailService {

    void SendMessage(MessageDto messageDto);
}
