package com.bigdata.tikitacar.user.service;

import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import com.bigdata.tikitacar.user.entity.User;

public interface UserService {

    // Create
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto);

    // Read
    public int findEmailDuplicate(String email);
    public int findNicknameDuplicate(String nickname);

    // Update
    public User findUserByEmail(String email);
}
