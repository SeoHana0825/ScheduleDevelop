package com.user.dto;

import lombok.Getter;

@Getter
public class UserRegisterRequest {

    private String name;
    private String email;
    private String password;
}
