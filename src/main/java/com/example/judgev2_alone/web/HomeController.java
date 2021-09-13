package com.example.judgev2_alone.web;

import com.example.judgev2_alone.security.CurrentUser;
import com.example.judgev2_alone.service.CommentService;
import com.example.judgev2_alone.service.ExerciseService;
import com.example.judgev2_alone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final UserService userService;
    private final CommentService commentService;

    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, UserService userService,
                          CommentService commentService) {
        this.currentUser = currentUser;
        this.exerciseService = exerciseService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if(currentUser.isAnonymous()) {
            return "index";
        }

        model.addAttribute("exercises",this.exerciseService.findAllNames());

        model.addAttribute("topStudent",this.userService.findTop3UsersByHomeworksSent());

        model.addAttribute("avg",this.commentService.findAvgScore());

        model.addAttribute("userCount",this.userService.getUserCount());

        model.addAttribute("scoreMap",this.commentService.findScoreMap());

        return "home";

    }

}
