package com.example.judgev2_alone.web;

import com.example.judgev2_alone.model.bindingModel.ExerciseBindingModel;
import com.example.judgev2_alone.model.service.ExerciseServiceModel;
import com.example.judgev2_alone.service.ExerciseService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/exercises")
public class ExerciseController {

    private final ModelMapper modelMapper;
    private final ExerciseService exerciseService;

    public ExerciseController(ModelMapper modelMapper, ExerciseService exerciseService) {
        this.modelMapper = modelMapper;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("exerciseBindingModel")) {
            model.addAttribute("exerciseBindingModel",new ExerciseBindingModel());
        }

        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute ExerciseBindingModel exerciseBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseBindingModel",exerciseBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exerciseBindingModel",
                    bindingResult);

            return "redirect:add";
        }

        ExerciseServiceModel exerciseServiceModel = modelMapper.map(exerciseBindingModel,ExerciseServiceModel.class);
        
        this.exerciseService.addExercise(exerciseServiceModel);
        
        return "redirect:/";

    }
}
