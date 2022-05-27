package com.bitirme.login.service;


import com.bitirme.login.dto.UserAdditionRequest;
import com.bitirme.login.model.User;

import java.util.List;

public interface UserService {
    User createNewUser(UserAdditionRequest request);
    User createNewUser(User user);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findAll();
}
