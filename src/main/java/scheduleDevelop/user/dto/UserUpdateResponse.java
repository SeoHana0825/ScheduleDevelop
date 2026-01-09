package scheduleDevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponse {

    private final String name;
    private final String email;
    private final String password;

    public UserUpdateResponse(
            String name,
            String email,
            String password,
            LocalDateTime updatedDate
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
