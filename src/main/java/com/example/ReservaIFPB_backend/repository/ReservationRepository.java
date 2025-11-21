package com.example.ReservaIFPB_backend.repository;

import com.example.ReservaIFPB_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByTeacherId(Long teacherId);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND (:newStartTime < r.endTime AND :newEndTime > r.startTime)")
    List<Reservation> findOverlappingReservations(
            @Param("roomId") Long roomId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime
    );

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.room.id = :roomId " +
            "AND r.id <> :reservationId " +
            "AND (:newStartTime < r.endTime AND :newEndTime > r.startTime)")
    List<Reservation> findOverlappingReservationsExcludingSelf(
            @Param("roomId") Long roomId,
            @Param("newStartTime") LocalDateTime newStartTime,
            @Param("newEndTime") LocalDateTime newEndTime,
            @Param("reservationId") Long reservationId
    );
}