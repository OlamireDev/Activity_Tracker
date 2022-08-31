package com.olamireDev.ActivtyTracker.service;

import com.olamireDev.ActivtyTracker.DTO.ActivityDTO;
import com.olamireDev.ActivtyTracker.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;

import com.olamireDev.ActivtyTracker.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ActivityService {

    public ActivityRepository  activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public List<ActivityDTO> getActivity(Long id){
        return null;
    }

    public void Save(Activity activity){}
    
}
