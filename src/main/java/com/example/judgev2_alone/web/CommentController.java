package com.example.judgev2_alone.web;

import com.example.judgev2_alone.model.bindingModel.CommentAddBindingModel;
import com.example.judgev2_alone.model.service.CommentServiceModel;
import com.example.judgev2_alone.model.view.HomeworkViewModel;
import com.example.judgev2_alone.service.CommentService;
import com.example.judgev2_alone.service.HomeworkService;
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
@RequestMapping("/comments")
public class CommentController {

    private final HomeworkService homeworkService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;

    public CommentController(HomeworkService homeworkService, ModelMapper modelMapper, CommentService commentService) {
        this.homeworkService = homeworkService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }

    @GetMapping("/add")
    public String addComment(Model model) {

        if (!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        HomeworkViewModel homeworkViewModel = modelMapper.map(this.homeworkService.findHomework(), HomeworkViewModel.class);

        model.addAttribute("homework",homeworkViewModel);

        return "homework-check";
    }

    @PostMapping("/add")
    public String addCommentConfirm(@Valid @ModelAttribute CommentAddBindingModel commentAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel",commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel",
                    bindingResult);

            return "redirect:add";
        }

        CommentServiceModel commentServiceModel = modelMapper.map(commentAddBindingModel,CommentServiceModel.class);

        this.commentService.addComment(commentServiceModel,commentAddBindingModel.getHomeworkId());

        return "redirect:/";
    }
}
