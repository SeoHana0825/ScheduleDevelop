package scheduleDevelop.user.dto;

import lombok.Getter;

@Getter
public class UserCreateRequest {

    private String name;
    private String email;
    private String password;
}
