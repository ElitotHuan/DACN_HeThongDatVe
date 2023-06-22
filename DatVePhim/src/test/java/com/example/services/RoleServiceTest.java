package com.example.services;

import com.example.models.Role;
import com.example.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void testGetAllRoles() {
        List<Role> roles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Role role = new Role();
            role.setId(i + 1);
            role.setName("Role" + (i + 1));
            roles.add(role);
        }

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = roleService.getAllRoles();
        assertEquals(roles.size(), result.size());
    }
}
