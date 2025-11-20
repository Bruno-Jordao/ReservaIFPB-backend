package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Role;
import com.example.ReservaIFPB_backend.entity.User;
import com.example.ReservaIFPB_backend.repository.UserRepository;
import com.example.ReservaIFPB_backend.web.dto.UserCreateDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Transactional
    public User saveUser(UserCreateDto dto){
        User user = UserMapper.toUser(dto);


        if (userRepository.count() == 0) {
            Role adminRole = roleService.findRoleByName("ADMIN");
            user.setRole(adminRole);
        } else {

            Role professorRole = roleService.findRoleByName("PROFESSOR");
            user.setRole(professorRole);
        }

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public User updateUserById(Long id, UserCreateDto dto) {
        User user = getUserById(id);

        user.setName(dto.getName());
        user.setRegistration(dto.getRegistration());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    @Transactional
    public void excludeUser(Long id){
        getUserById(id);
        userRepository.deleteById(id);
    }
}