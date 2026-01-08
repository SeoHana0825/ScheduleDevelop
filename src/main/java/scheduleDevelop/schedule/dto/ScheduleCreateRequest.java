package scheduleDevelop.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    private String title;
    private String text;
    // private String username; // userId 불러와서 필요없어짐

    private Long userId;
}
