package com.bigdata.tikitacar.user.service;

import com.bigdata.tikitacar.user.dto.request.UserLoginRequestDto;
import com.bigdata.tikitacar.user.dto.request.UserModifyRequestDto;
import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import com.bigdata.tikitacar.user.dto.response.UserFindResponseDto;
import com.bigdata.tikitacar.user.dto.response.UserLoginResponseDto;
import com.bigdata.tikitacar.user.entity.User;

public interface UserService {

    // Create
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto);

    // Read
    public int findEmailDuplicate(String email);
    public int findNicknameDuplicate(String nickname);
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);

    // Update
    public UserFindResponseDto findUserByEmail(String email);
    public String findPasswordByEmail(String email);
    public void modifyUserAuth(String email);
    public void modifyUserInfo(UserModifyRequestDto userModifyRequestDto);

    // Delete
    public void deleteUser(String email);
}
