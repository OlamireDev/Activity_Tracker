package com.olamireDev.ActivtyTracker.model;

import java.sql.Date;

import javax.persistence.*;

import com.olamireDev.ActivtyTracker.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "activity")
@Data
@Getter @Setter @NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="activity_id")
    private long activityId;

    @Column(name ="user_id")
    private long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name ="created")
    private Date createdAt;

    @Column(name = "updated")
    private Date updatedAt;

    @Column(name = "completed")
    private Date completedAt;

    public Activity(long activityId, long userId, String title, String description, Status status, Date createdAt,
                    Date updatedAt, Date completedAt){
            this.activityId =activityId;
            this.userId = userId;
            this.title =title;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.completedAt = completedAt;
        }
    
        public Activity(long activityId, long userId, String title, String description, Status status, Date createdAt){
            this.activityId = activityId;
            this.userId = userId;
            this.title =title;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
        }

        public Activity(long activityId, String title, String description, Status status){
            this.activityId = activityId;
            this.title =title;
            this.description = description;
            this.status = status;
        }

}
