package com.olamireDev.ActivtyTracker.controller;

import com.olamireDev.ActivtyTracker.DTO.UserDTO;
import com.olamireDev.ActivtyTracker.enums.Status;
import com.olamireDev.ActivtyTracker.model.Activity;
import com.olamireDev.ActivtyTracker.serviceImpl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            session.setAttribute("activity", activityService.getActivity(user.getUserId()));
            return "Homepage";
        }
        return "login";
    }

    @PostMapping("/id")
    public String getAct(@RequestParam("id") String id, HttpSession session, Model m){
        Long Id =Long.valueOf(id);
        System.out.println(session.getAttribute("activity"));
        List<Activity> list = (List<Activity>) session.getAttribute("activity");
        for(Activity a: list){
            if(a.getUserId() == Id){
               m.addAttribute("act", a);
            }
        }
        return "activitypage";
    }

    @PostMapping("/done")
    public String markAsDone(@RequestParam("id") String id){
        long Id = Long.valueOf(id);
        Date jDate = new Date();
        Activity activity = new Activity();
        activity.setActivityId(Id);
        activity.setStatus(Status.DONE);
        activity.setCompletedAt(new java.sql.Date(jDate.getTime()));
        activityService.Save(activity);
        return "redirect:/home";
    }
    
}
