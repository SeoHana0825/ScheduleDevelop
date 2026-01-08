package scheduleDevelop.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import scheduleDevelop.user.entity.User;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    public Schedule(String username, String title, String text) {
        this.username = username;
        this.title = title;
        this.text = text;
    }

    public void update(String username, String title, String text) {
        this.username = username;
        this.title = title;
        this.text = text;
    }

}
