package scheduleDevelop.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduleDevelop.user.dto.*;
import scheduleDevelop.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 일정 생성 - 유저명, 이메일, 작성일, 수정일
    @PostMapping("/users")
    public ResponseEntity<UserCreateResponse> create(
            @RequestBody UserCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserGetResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    // 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> getOne(
            @PathVariable Long userId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }

    // 유저 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponse> update(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public void delete(
            @PathVariable Long userId
    ) {
        userService.delete(userId);
    }

}
