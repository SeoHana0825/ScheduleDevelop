package com.user.controller;

import com.user.dto.*;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(
            @Valid
            @RequestBody UserRegisterRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(
            @Valid
            @RequestBody UserLoginRequest request,
            HttpSession httpSession
    ) {
        SessionUser sessionUser = userService.login(request);
        httpSession.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponse(sessionUser.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "loginUser", required = false)
                SessionUser sessionUser, HttpSession httpSession
    ) {
        if (sessionUser != null) {
            return ResponseEntity.badRequest().build();
        }
        httpSession.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<UserGetResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> getOne(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(id));
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> update(
            @Valid
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser
            @RequestBody UserUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(SessionUser, request));
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
            @RequestBody UserDeleteRequest request
    ) {
        userService.delete(id, request);
    }
}
