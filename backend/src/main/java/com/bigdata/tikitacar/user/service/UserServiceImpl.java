package com.bigdata.tikitacar.user.service;

import com.bigdata.tikitacar.user.dto.request.UserLoginRequestDto;
import com.bigdata.tikitacar.user.dto.request.UserModifyRequestDto;
import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import com.bigdata.tikitacar.user.dto.response.UserFindResponseDto;
import com.bigdata.tikitacar.user.dto.response.UserLoginResponseDto;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // Create
    @Override
    @Transactional
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

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();
        User user = userRepository.findByEmailAndPassword(email, password).orElse(null);

        UserLoginResponseDto userLoginResponseDto = null;


        if(user != null)
            userLoginResponseDto = UserLoginResponseDto.builder()
                    .email(email)
                    .nickname(user.getNickname())
                    .id(user.getId())
                    .auth(user.getAuth())
                    .build();

        return userLoginResponseDto;
    }

    @Override
    public UserFindResponseDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        UserFindResponseDto userFindResponseDto = null;

        if(user != null){
            userFindResponseDto = userFindResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickanme(user.getNickname())
                .birth(user.getBirth())
                .gender(user.getGender())
                .phone(user.getPhone())
                .address(user.getAddress())
                .addressDetail(user.getAddressDetail())
                .build();
        }

        return userFindResponseDto;
    }

    @Override
    public String findPasswordByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        return user.getPassword();
    }

    @Override
    public void modifyUserAuth(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user != null){
            user.updateUserAuth();
        }
    }

    @Override
    @Transactional
    public void modifyUserInfo(UserModifyRequestDto userModifyRequestDto) {
        User user = userRepository.findById(userModifyRequestDto.getId()).orElse(null);

        if(user != null){
            user.updateUserInfo(userModifyRequestDto);
        }
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user != null){
            userRepository.deleteById(user.getId());
        }
    }

}
