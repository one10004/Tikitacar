package com.bigdata.tikitacar.util;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject,String text);
}
