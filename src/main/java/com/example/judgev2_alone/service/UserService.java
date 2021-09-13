package com.example.judgev2_alone.service;

import com.example.judgev2_alone.model.entity.User;
import com.example.judgev2_alone.model.enums.RoleEnum;
import com.example.judgev2_alone.model.service.UserServiceModel;
import com.example.judgev2_alone.model.view.ProfileViewModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    void saveUserToDB(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logoutUser();

    List<String> getAllNames();

    void changeRole(String username, RoleEnum valueOf);

    User findUserByUsername(String username);

    ProfileViewModel findUserById(Long id);

    User findAuthorById(Long id);

    Set<String> findTop3UsersByHomeworksSent();

    Integer getUserCount();


}
