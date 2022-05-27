package com.bitirme.login.service;

import com.bitirme.login.model.Role;
import com.bitirme.login.model.RoleType;

public interface RoleService {
    Role findByName(RoleType name);
}
