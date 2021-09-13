package com.example.judgev2_alone.service.impl;

import com.example.judgev2_alone.model.entity.Comment;
import com.example.judgev2_alone.model.entity.Homework;
import com.example.judgev2_alone.model.entity.User;
import com.example.judgev2_alone.model.service.CommentServiceModel;
import com.example.judgev2_alone.repository.CommentRepository;
import com.example.judgev2_alone.security.CurrentUser;
import com.example.judgev2_alone.service.CommentService;
import com.example.judgev2_alone.service.HomeworkService;
import com.example.judgev2_alone.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final HomeworkService homeworkService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper,
                              HomeworkService homeworkService, UserService userService, CurrentUser currentUser) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.homeworkService = homeworkService;
        this.userService = userService;

        this.currentUser = currentUser;
    }

    @Override
    public void addComment(CommentServiceModel commentServiceModel, Long homeworkId) {

        Comment comment = modelMapper.map(commentServiceModel, Comment.class);

        Homework homework = this.homeworkService.findHomeworkById(homeworkId);
        comment.setHomework(homework);

        User author = this.userService.findAuthorById(currentUser.getId());

        comment.setAuthor(author);

        commentRepository.save(comment);
    }

    @Override
    public Double findAvgScore() {
        return this.commentRepository.findAverageScore();
    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer> scoreMap = initScoreMap();

        this.commentRepository.findAll()
                .forEach(comment -> {
                    Integer score = comment.getScore();
                    scoreMap.put(score,scoreMap.get(score) + 1);
                });
        return scoreMap;
    }


    private Map<Integer, Integer> initScoreMap() {
        Map<Integer, Integer> initMap = new HashMap<>();

        for (int i = 2; i <= 6; i++) {
            initMap.put(i, 0);
        }

        return initMap;
    }
}
