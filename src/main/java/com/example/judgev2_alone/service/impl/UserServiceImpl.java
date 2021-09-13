package com.example.judgev2_alone.service.impl;

import com.example.judgev2_alone.model.entity.User;
import com.example.judgev2_alone.model.enums.RoleEnum;
import com.example.judgev2_alone.model.service.UserServiceModel;
import com.example.judgev2_alone.model.view.ProfileViewModel;
import com.example.judgev2_alone.repository.UserRepository;
import com.example.judgev2_alone.security.CurrentUser;
import com.example.judgev2_alone.service.HomeworkService;
import com.example.judgev2_alone.service.RoleService;
import com.example.judgev2_alone.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;



    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void saveUserToDB(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        if (this.userRepository.count() == 0){
            user.setRole(roleService.findRole(RoleEnum.ADMIN));
        } else {
            user.setRole(roleService.findRole(RoleEnum.USER));
        }

        this.userRepository.save(user);

    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        User user = this.userRepository.findByUsernameAndPassword(username, password).orElse(null);

        if (user != null) {
            return modelMapper.map(user, UserServiceModel.class);
        }

        return null;

    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser.setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRoleEnum(userServiceModel.getRole().getRoleEnum());

    }

    @Override
    public void logoutUser() {
        this.currentUser.setUsername(null)
                .setId(null)
                .setRoleEnum(null);
    }

    @Override
    public List<String> getAllNames() {
        return this.userRepository.getAllNames();
    }

    @Override
    public void changeRole(String username, RoleEnum role) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        if(user != null && user.getRole().getRoleEnum() != role) {
            user.setRole(roleService.findRole(role));
            this.userRepository.save(user);
        }

    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public ProfileViewModel findUserById(Long id) {

        User user = this.userRepository.findById(id).orElse(null);

        ProfileViewModel profileViewModel= modelMapper.map(user,ProfileViewModel.class);

        profileViewModel.setHomeworks(user.getHomeworks()
                .stream()
                .map(homework -> homework.getExercise().getName())
                .collect(Collectors.toList()));

        return profileViewModel;

    }

    @Override
    public User findAuthorById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public Set<String> findTop3UsersByHomeworksSent() {
        return this.userRepository.findTop3UsersByHomework()
                .stream()
                .limit(3)
                .collect(Collectors.toSet());
    }

    @Override
    public Integer getUserCount() {
        return this.userRepository.getUserCount();
    }


}
