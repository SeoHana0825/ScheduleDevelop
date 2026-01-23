package com.user.service;

import com.user.dto.*;
import com.user.entity.User;
import com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest request) {
        if (request.getPassword() == null) {
            throw new IllegalArgumentException("비밀번호는 필수값입니다.");
        }
        if (request.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8글자 이상입니다.");
        }
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
    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        // 비밀번호가 일치하지 않을 때
        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 안습니다.");
        }
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
    public void delete(Long id, UserDeleteRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당 유저가 없습니다.")
        );
        // 이메일이 같지 않다면
        if (!ObjectUtils.nullSafeEquals(request.getEmail(), user.getPassword())) {
            throw new IllegalStateException("이메일이 일치하지 않습니다.");
        }
        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }


    @Transactional(readOnly = true)
    public SessionUser login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("해당 유저가 없습니다.")
        );
        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return new SessionUser(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }
}
