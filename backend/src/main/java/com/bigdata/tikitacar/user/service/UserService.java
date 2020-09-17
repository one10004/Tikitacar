package com.bigdata.tikitacar.user.service;

import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;

public interface UserService {

    // Create
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto);

    // Read
    public int findEmailDuplicate(String email);
}
