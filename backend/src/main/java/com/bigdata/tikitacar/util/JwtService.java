package com.bigdata.tikitacar.util;

public interface JwtService {
    public String generateToken(String id);

    public boolean isValid(String id, String token) throws Exception;

    boolean isValid(String token) throws Exception;

    public String getEmailFromToken(String token);

    public Long getValidTime();
}
