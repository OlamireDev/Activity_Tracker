package com.olamireDev.ActivtyTracker.repository;

import com.olamireDev.ActivtyTracker.DTO.ActivityDTO;
import org.springframework.data.repository.CrudRepository;

import com.olamireDev.ActivtyTracker.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends CrudRepository<Activity, Long>{
    public List<Activity> findAllByUserId (Long id);

    public Optional<Activity> findByActivityId(Long id);
}
