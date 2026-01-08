package scheduleDevelop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String text;
    private String password;

    public Schedule(String username, String title, String text, String password) {
        this.username = username;
        this.title = title;
        this.text = text;
        this.password = password;
    }

    public void updateTitleAndUsername(String username, String title) {
        this.username = username;
        this.title = title;
    }
}
