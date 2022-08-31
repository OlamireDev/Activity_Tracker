package com.olamireDev.ActivtyTracker.DTO;

import com.olamireDev.ActivtyTracker.enums.Status;
import com.olamireDev.ActivtyTracker.model.Activity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor @Data
public class ActivityDTO extends Activity {

    public ActivityDTO(long activityId, String title, String description, Status status){
        super(activityId, title, description,status);
    }
}
