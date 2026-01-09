package scheduleDevelop.user.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import scheduleDevelop.user.dto.*;
import scheduleDevelop.user.entity.User;
import scheduleDevelop.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 로그인
    @Transactional(readOnly = true)
    public UserLoginResponse login(
            @Valid UserLoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 이메일입니다.")
        );

        // 비밀번호가 같지 않으면
        // !ObjectUtils.nullSafeEquals : null 및 버그 방지를 위해 사용
        if (!ObjectUtils.nullSafeEquals(user.getPassword(), request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        // 비밀번호가 같으면
        return new UserLoginResponse(
                user.getId(),
                user.getEmail()
        );
    }

    // 1. 유저 생성 (회원가입)
    @Transactional
    public UserCreateResponse save(UserCreateRequest request) {

        // 비밀번호 검증 (null 포함)
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8글자 이상이어야 합니다.");
        }
        if (request.getName() == null || request.getName().length() > 5) {
            throw new IllegalArgumentException("이름은 5글자 이내로 작성해주세요.");
        }

        // 검증 후 유저 생성
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
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

    // 전체 유저 조회
    @Transactional(readOnly = true)
    public List<UserGetResponse> findAll() {
        List<User> users = userRepository.findAll();
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

    // 선택 유저 조회
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

    // 유저 수정
    @Transactional
    public UserUpdateResponse update(Long userId, UserUpdateRequest request) {
        // 유저 조회가 없을 때
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
        );
        // 비밀번호 검증 (null 포함)
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8글자 이상이어야 합니다.");
        }

        if (request.getName() == null || request.getName().length() > 5) {
            throw new IllegalArgumentException("이름은 5글자 이내로 작성해주세요.");
        }

        // 수정 가능한 필드만 변경
        user.update(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return new UserUpdateResponse(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUpdatedDate()
        );
    }

    // 유저 삭제
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