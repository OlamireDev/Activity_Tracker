package com.olamireDev.ActivtyTracker.repository;


import org.springframework.data.repository.CrudRepository;

import com.olamireDev.ActivtyTracker.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends CrudRepository<Activity, Long>{
        List<Activity> findAllByUserId (Long id);

        Optional<Activity> findByActivityId(Long id);

}
