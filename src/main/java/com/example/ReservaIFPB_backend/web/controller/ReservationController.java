package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Reservation;
import com.example.ReservaIFPB_backend.service.ReservationService;
import com.example.ReservaIFPB_backend.web.dto.ReservationCreateDto;
import com.example.ReservaIFPB_backend.web.dto.ReservationResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.ReservationMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationCreateDto dto){
        Reservation newReservation = reservationService.saveReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservationMapper.toDto(newReservation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservationId(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(ReservationMapper.toDto(reservation));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAll(){
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(ReservationMapper.toListDto(reservations));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByTeacher(@PathVariable Long teacherId){
        List<Reservation> reservations = reservationService.getReservationsByTeacherId(teacherId);
        return ResponseEntity.ok(ReservationMapper.toListDto(reservations));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody ReservationCreateDto dto
    ) {
        Reservation reservationUpdate = reservationService.updateReservationById(id, dto);
        return ResponseEntity.ok(ReservationMapper.toDto(reservationUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        reservationService.excludeReservation(id);
        return ResponseEntity.noContent().build();
    }
}