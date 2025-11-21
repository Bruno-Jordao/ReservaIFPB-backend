package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Role;
import com.example.ReservaIFPB_backend.exception.RoleNotFoundException;
import com.example.ReservaIFPB_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new RoleNotFoundException(name)
        );
    }
}