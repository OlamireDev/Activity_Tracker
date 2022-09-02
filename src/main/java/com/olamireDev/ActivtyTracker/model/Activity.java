package com.olamireDev.ActivtyTracker.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

import com.olamireDev.ActivtyTracker.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated")
    @UpdateTimestamp
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity activity)) return false;
        return getActivityId() == activity.getActivityId() && getUserId() == activity.getUserId() && Objects.equals(getTitle(), activity.getTitle()) && Objects.equals(getDescription(), activity.getDescription()) && getStatus() == activity.getStatus() && Objects.equals(getCreatedAt(), activity.getCreatedAt()) && Objects.equals(getUpdatedAt(), activity.getUpdatedAt()) && Objects.equals(getCompletedAt(), activity.getCompletedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActivityId(), getUserId(), getTitle(), getDescription(), getStatus(), getCreatedAt(), getUpdatedAt(), getCompletedAt());
    }
}
