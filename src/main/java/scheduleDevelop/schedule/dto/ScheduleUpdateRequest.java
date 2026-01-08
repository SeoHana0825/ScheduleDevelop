package scheduleDevelop.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    // private String username;
    private String title;
    private String text;

    private Long userId;
}
