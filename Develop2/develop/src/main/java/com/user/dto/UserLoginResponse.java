package com.user.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {

    private final Long id;

    public UserLoginResponse(Long id) {
        this.id = id;
    }
}
