package scheduleDevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public UserUpdateResponse(
            Long id,
            String name,
            String email,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
