package scheduleDevelop.user.dto;

import lombok.Getter;

@Getter
public class UserLoginResponse {

    private final Long id;
    private final String email;

    public UserLoginResponse(
            Long id,
            String email
    ) {
        this.id = id;
        this.email = email;
    }
}
