package com.example.ReservaIFPB_backend.repository;

import com.example.ReservaIFPB_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM User u " +
            "WHERE UPPER(u.email) = UPPER(:email) OR UPPER(u.registration) = UPPER(:registration)")
    boolean existsByEmailOrRegistrationIgnoreCase(@Param("email") String email, @Param("registration") String registration);
}