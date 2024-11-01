package com.urbanwardrobe.app.service;

import com.urbanwardrobe.app.exception.UserException;
import com.urbanwardrobe.app.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;

    public List<User> findAllUsers();
}
