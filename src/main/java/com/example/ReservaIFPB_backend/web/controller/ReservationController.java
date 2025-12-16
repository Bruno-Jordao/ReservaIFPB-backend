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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Reservations", description = "Endpoints for managing reservations")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "Create a new Reservation", description = "Register a new room or space reservation")
    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationCreateDto dto){
        Reservation newReservation = reservationService.saveReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservationMapper.toDto(newReservation));
    }

    @Operation(summary = "Reservation search by ID", description = "Returns the details of a specific booking")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservationId(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(ReservationMapper.toDto(reservation));
    }

    @Operation(summary = "List all reservations", description = "Returns a complete list of all reservations")
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAll(){
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(ReservationMapper.toListDto(reservations));
    }

    @Operation(summary = "List of reservations by Professor ID", description = "Returns all bookings made by a specific teacher")
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByTeacher(@PathVariable Long teacherId){
        List<Reservation> reservations = reservationService.getReservationsByTeacherId(teacherId);
        return ResponseEntity.ok(ReservationMapper.toListDto(reservations));
    }

    @Operation(summary = "Update a reservation", description = "Change the details of an existing reservation by ID")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody ReservationCreateDto dto
    ) {
        Reservation reservationUpdate = reservationService.updateReservationById(id, dto);
        return ResponseEntity.ok(ReservationMapper.toDto(reservationUpdate));
    }

    @Operation(summary = "Remove a reservation", description = "Remove a reservation from the system by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        reservationService.excludeReservation(id);
        return ResponseEntity.noContent().build();
    }
}