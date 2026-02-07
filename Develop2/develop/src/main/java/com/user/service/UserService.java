package com.user.service;

import com.common.config.PasswordEncoder;
import com.user.dto.*;
import com.user.entity.User;
import com.user.error.PasswordMismatchException;
import com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        String password = request.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
//
//        if (request.getPassword() == null) {
//            throw new IllegalArgumentException("비밀번호는 필수값입니다.");
//        }
//        if (request.getPassword().length() < 8) {
//            throw new IllegalArgumentException("비밀번호는 8글자 이상입니다.");
//        }
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);
        return new UserRegisterResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<UserGetResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserGetResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                )).toList();
    }

    @Transactional(readOnly = true)
    public UserGetResponse findOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        return new UserGetResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public UserUpdateResponse update(SessionUser sessionUser, UserUpdateRequest request) {
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        // 비밀번호가 일치하지 않을 때 (passwordMismatch 객체 사용)
        if (!matches) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다");
        }
        // 비밀번호가 일치하지 않을 때
//        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
//            throw new IllegalStateException("비밀번호가 일치하지 안습니다.");
//        }
        user.update(request.getName());

        return new UserUpdateResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(SessionUser sessionUser, UserDeleteRequest request) {
        User user = userRepository.findById(sessionUser.getId()).orElseThrow(
                () -> new IllegalStateException("해당 유저가 없습니다.")
        );
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        // 이메일이 같지 않다면
        if (!ObjectUtils.nullSafeEquals(request.getEmail(), user.getPassword())) {
            throw new IllegalStateException("이메일이 일치하지 않습니다.");
        }
        // 비밀번호가 일치하지 않을 때 (passwordMismatch 객체 사용)
        if (!matches) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다");
        }

//        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
//            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
//        }
        userRepository.delete(user);
    }


    @Transactional(readOnly = true)
    public SessionUser login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("해당 유저가 없습니다.")
        );
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        // 비밀번호가 일치하지 않을 때 (passwordMismatch 객체 사용)
        if (!matches) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다");
        }

//        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
//            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
//        }

        return new SessionUser(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }
}
