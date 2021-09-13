package com.example.judgev2_alone.web;

import com.example.judgev2_alone.model.bindingModel.HomeworkAddBindingModel;
import com.example.judgev2_alone.service.ExerciseService;
import com.example.judgev2_alone.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("homework")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;

    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/add")
    public String homeworkAdd(Model model){

        if(!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel",new HomeworkAddBindingModel());
            model.addAttribute("isLate",false);
        }

        model.addAttribute("exercises",this.exerciseService.findAllNames());
        return "homework-add";
    }

    @PostMapping("/add")
    public String homeworkAddConfirm(@Valid @ModelAttribute HomeworkAddBindingModel homeworkAddBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel",homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel",
                    bindingResult);

            return "redirect:add";
        }

        boolean isLate = this.exerciseService.checkIfisLate(homeworkAddBindingModel.getExercise());

        if(isLate) {
           redirectAttributes.addFlashAttribute("homeworkAddBindingModel",homeworkAddBindingModel);
           redirectAttributes.addFlashAttribute("isLate",isLate);

           return "redirect:add";
        }

        this.homeworkService.addHomework(homeworkAddBindingModel.getExercise(),
                homeworkAddBindingModel.getGitAddress());

        return "redirect:/";
    }
}
