package scheduleDevelop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponse {

    private final Long id;
    private final String username;
    private final String title;
    private final String text;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public ScheduleGetResponse(
            Long id,
            String username,
            String title,
            String text,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.text = text;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
