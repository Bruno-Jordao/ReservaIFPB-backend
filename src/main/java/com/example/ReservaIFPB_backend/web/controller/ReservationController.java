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

@Tag(name = "Reservations", description = "Endpoints para gerenciamento de Reservas")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "Cria uma nova reserva", description = "Registra uma nova reserva de sala ou espaço")
    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationCreateDto dto){
        Reservation newReservation = reservationService.saveReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservationMapper.toDto(newReservation));
    }

    @Operation(summary = "Busca reserva por ID", description = "Retorna os detalhes de uma reserva específica")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservationId(@PathVariable Long id){
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(ReservationMapper.toDto(reservation));
    }

    @Operation(summary = "Lista todas as reservas", description = "Retorna uma lista completa de todas as reservas")
    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAll(){
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(ReservationMapper.toListDto(reservations));
    }

    @Operation(summary = "Lista reservas por ID do Professor", description = "Retorna todas as reservas feitas por um professor específico")
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByTeacher(@PathVariable Long teacherId){
        List<Reservation> reservations = reservationService.getReservationsByTeacherId(teacherId);
        return ResponseEntity.ok(ReservationMapper.toListDto(reservations));
    }

    @Operation(summary = "Atualiza uma reserva", description = "Altera os dados de uma reserva existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(
            @PathVariable Long id,
            @Valid @RequestBody ReservationCreateDto dto
    ) {
        Reservation reservationUpdate = reservationService.updateReservationById(id, dto);
        return ResponseEntity.ok(ReservationMapper.toDto(reservationUpdate));
    }

    @Operation(summary = "Exclui uma reserva", description = "Remove uma reserva do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        reservationService.excludeReservation(id);
        return ResponseEntity.noContent().build();
    }
}