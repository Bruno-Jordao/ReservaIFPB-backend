package com.example.ReservaIFPB_backend.repository;

import com.example.ReservaIFPB_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByTeacherId(Long teacherId);
}