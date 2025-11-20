package com.example.ReservaIFPB_backend.repository;

import com.example.ReservaIFPB_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}