package com.bigdata.tikitacar.user.service;

import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // Create
    @Override
    public void registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        User user = User.builder()
                .password(userRegisterRequestDto.getPassword())
                .email(userRegisterRequestDto.getEmail())
                .nickname(userRegisterRequestDto.getNickname())
                .birth(userRegisterRequestDto.getBirth())
                .gender(userRegisterRequestDto.getGender())
                .phone(userRegisterRequestDto.getPhone())
                .address(userRegisterRequestDto.getAddress())
                .addressDetail(userRegisterRequestDto.getAddressDetail())
                .build();

        userRepository.save(user);
        return;
    }

    @Override
    public int findEmailDuplicate(String email) {
        return userRepository.countByEmail(email);
    }

    @Override
    public int findNicknameDuplicate(String nickname) {
        return userRepository.countByNickname(nickname);
    }
}
