package com.user.dto;

import lombok.Getter;

@Getter
public class UserLoginRequest {

    private String name;
    private String email;
    private String password;
}
