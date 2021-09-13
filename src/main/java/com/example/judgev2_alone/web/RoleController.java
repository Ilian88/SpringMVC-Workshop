package com.example.judgev2_alone.web;

import com.example.judgev2_alone.model.enums.RoleEnum;
import com.example.judgev2_alone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String role(Model model) {

        model.addAttribute("names",this.userService.getAllNames());

        return "role-add";
    }

    @PostMapping("/add")
    public String roleAddConfirm(@RequestParam String username,
                                 @RequestParam String role) {

       this.userService.changeRole(username, RoleEnum.valueOf(role.toUpperCase()));

        return "redirect:/";
    }
}
