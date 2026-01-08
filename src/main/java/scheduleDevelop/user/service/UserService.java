package scheduleDevelop.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scheduleDevelop.user.dto.*;
import scheduleDevelop.user.entity.User;
import scheduleDevelop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 1. 유저 생성
    @Transactional
    public UserCreateResponse save(UserCreateRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail()
        );

        User savedUser = userRepository.save(user);
        return new UserCreateResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCreatedDate(),
                savedUser.getUpdatedDate()
        );
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<UserGetResponse> findAll() {
        List<User>  users = userRepository.findAll();
        List<UserGetResponse> dtos = new ArrayList<>();
        for (User user : users) {
            UserGetResponse dto = new UserGetResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCreatedDate(),
                    user.getUpdatedDate()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public UserGetResponse findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
        );

        return new UserGetResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }

    // 일정 수정
    @Transactional
    public UserUpdateResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
        );

        user.update(
                request.getName(),
                request.getEmail()
        );

        return new UserUpdateResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }

        // 일정 삭제
        @Transactional
        public void delete(Long userId) {
            boolean existence = userRepository.existsById(userId);
            if (!existence) {
                throw new IllegalArgumentException("유저가 존재하지 않습니다");
            }
            // 유저가 존재할 경우
            userRepository.deleteById(userId);
        }
    }