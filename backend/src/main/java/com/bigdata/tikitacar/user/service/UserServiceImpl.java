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

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {

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

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        User user = Optional.of(userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new NoSuchElementException("(로그인 중 오류 발생)해당 이메일과 비밀번호로 유저를 조회할 수 없음."))).get();

        UserLoginResponseDto userLoginResponseDto = null;


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
        User user = Optional.of(userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("이메일에 해당하는 유저가 존재하지 않음."))).get();
        UserFindResponseDto userFindResponseDto = null;

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

        return userFindResponseDto;
    }

    @Override
    public String findPasswordByEmail(String email) {
        User user = Optional.of(userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("이메일에 해당하는 유저가 존재하지 않음."))).get();

        return user.getPassword();
    }

    @Override
    public void modifyUserAuth(String email) {
        User user = Optional.of(userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("이메일에 해당하는 유저가 존재하지 않음."))).get();

        user.updateUserAuth();
    }

    @Override
    @Transactional
    public void modifyUserInfo(UserModifyRequestDto userModifyRequestDto) {
        User user = Optional.of(userRepository.findById(userModifyRequestDto.getId()).orElseThrow(() -> new NoSuchElementException("id에 해당하는 유저가 존재하지 않음."))).get();

        user.updateUserInfo(userModifyRequestDto);
    }

    @Override
    @Transactional
    public String findPassword(String email) {
        User user = Optional.of(userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("이메일에 해당하는 유저가 존재하지 않음."))).get();

        Random rand = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<10;i++){
            int idx = rand.nextInt(3);
            switch (idx){
                case 0:
                    sb.append((char)(rand.nextInt(26) + 97));
                    break;
                case 1:
                    sb.append((char)(rand.nextInt(26)+65));
                    break;
                case 2:
                    sb.append(rand.nextInt(10));
                    break;
            }
        }

        user.randomPassword(sb.toString());
        return sb.toString();
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User user = Optional.of(userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("이메일에 해당하는 유저가 존재하지 않음."))).get();

        userRepository.deleteById(user.getId());
    }

}
