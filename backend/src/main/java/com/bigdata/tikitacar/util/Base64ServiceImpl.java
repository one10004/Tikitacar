package com.bigdata.tikitacar.util;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class Base64ServiceImpl implements Base64Service{

    public String encode(String input) {
        byte[] message =input.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }

    public String decode(String input) {
        byte[] message=Base64.getDecoder().decode(input);
        return new String(message,StandardCharsets.UTF_8);
    }
}
