package com.bitirme.login.service.impl;

import com.bitirme.login.exception.RoleNotFoundException;
import com.bitirme.login.model.Role;
import com.bitirme.login.model.RoleType;
import com.bitirme.login.repository.RoleRepository;
import com.bitirme.login.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(RoleType name) {
        Objects.requireNonNull(name, "role name cannot be null");
        return roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);
    }
}
