package com.schedule.entity;

import com.common.baseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends baseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String title;
    private String content;

    public Schedule(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
