package com.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String createdAt;
    private final String updatedAt;

    public UserUpdateResponse(
            Long id,
            String name,
            String email,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
