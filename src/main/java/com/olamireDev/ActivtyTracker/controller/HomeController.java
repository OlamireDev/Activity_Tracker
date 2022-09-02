package com.olamireDev.ActivtyTracker.controller;

import com.olamireDev.ActivtyTracker.DTO.ActivityDTO;
import com.olamireDev.ActivtyTracker.DTO.UserDTO;
import com.olamireDev.ActivtyTracker.enums.Status;
import com.olamireDev.ActivtyTracker.model.Activity;
import com.olamireDev.ActivtyTracker.serviceImpl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    private final ActivityServiceImpl activityService;

    @Autowired
    public HomeController(ActivityServiceImpl activityService){
        this.activityService = activityService;
    }

    @GetMapping("/home")
    public String home(HttpSession session){
        if(session.getAttribute("user") != null){
            UserDTO user = (UserDTO) session.getAttribute("user");
            session.setAttribute("activity", activityService.getActivities(user.getUserId()));
            return "Homepage";
        }
        return "login";
    }

    @PostMapping("/id")
    public String getAct(@RequestParam("id") String id, HttpSession session, Model m){
        Long Id =Long.valueOf(id);
        List<Activity> list = (List<Activity>) session.getAttribute("activity");
        for(Activity a: list){
            if(a.getActivityId() == Id){
                ActivityDTO newer = new ActivityDTO(a.getUserId(), a.getTitle(), a.getDescription(), a.getStatus());
                newer.setActivityId(a.getActivityId());
                System.out.println(newer.getActivityId());
                m.addAttribute("dto", newer);
                break;
            }
        }
        return "activity-page";
    }

    @PostMapping("/new")
    String newActivity(Model m){
        m.addAttribute("dto", new ActivityDTO());
        return "addActivity";
    }

    @PostMapping("/add")
    String addActivity(@ModelAttribute("dto") ActivityDTO activityDTO, HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        System.out.println(userDTO.getUserId());
        Activity activity = new Activity();
        activity.setUserId(userDTO.getUserId());
        activity.setTitle(activityDTO.getTitle());
        activity.setDescription(activityDTO.getDescription());
        activity.setStatus(activityDTO.getStatus());
        System.out.println(activity.getUserId());
        activityService.Save(activity);
        return "redirect:/home";
    }

    @PostMapping("/done")
    public String markAsDone(@RequestParam("id") String id){
        long Id = Long.valueOf(id);
        Date jDate = new Date();
        Activity activity = activityService.getActivity(Id);
        activity.setActivityId(Id);
        activity.setStatus(Status.DONE);
        activity.setCompletedAt(new java.sql.Date(jDate.getTime()));
        activityService.Save(activity);
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateAct(@ModelAttribute("dto") ActivityDTO activityDTO){
        System.out.println(activityDTO.getActivityId());
        Activity activity = activityService.getActivity(activityDTO.getActivityId());
        if(activityDTO.getTitle().length() > 0) {
            activity.setTitle(activityDTO.getTitle());
        }
        if(activityDTO.getDescription().length() > 0) {
            activity.setDescription(activityDTO.getDescription());
        }
        activity.setStatus(activityDTO.getStatus());
        System.out.println(activity.getStatus());
        activityService.Save(activity);
        return "redirect:/home";
    }

    @PostMapping ("/delete")
    public String deleteAct(@RequestParam("id") String id){
        Long actId = Long.valueOf(id);
        activityService.deleteActivity(actId);
        return"redirect:/home";
    }
    
}
