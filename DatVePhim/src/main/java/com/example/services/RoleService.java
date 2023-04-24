package com.example.services;

import com.example.models.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleService extends GeneralService<Role> {
    Role findByName(String name);
}
