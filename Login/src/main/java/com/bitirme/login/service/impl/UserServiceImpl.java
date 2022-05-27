package com.bitirme.login.service.impl;


import com.bitirme.login.dto.UserAdditionRequest;
import com.bitirme.login.exception.UserNotFoundException;
import com.bitirme.login.model.Role;
import com.bitirme.login.model.RoleType;
import com.bitirme.login.model.User;
import com.bitirme.login.repository.UserRepository;
import com.bitirme.login.service.RoleService;
import com.bitirme.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public User createNewUser(UserAdditionRequest request) {
        User user = User
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        Set<String> strRoles = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        Role role = roleService.findByName(RoleType.ROLE_USER);
        roles.add(role);
        user.setRoles(roles);
        return createNewUser(user);
    }
    @Override
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Boolean existsByUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null");
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        Objects.requireNonNull(email, "email cannot be null");
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
