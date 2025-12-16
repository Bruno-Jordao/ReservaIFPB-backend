package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.service.RoomService;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import com.example.ReservaIFPB_backend.web.dto.RoomResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.RoomMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Rooms", description = "Endpoints para gerenciamento de Salas/Espaços")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Cria uma nova sala/espaço", description = "Registra uma nova sala que pertence a um bloco")
    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@Valid @RequestBody RoomCreateDto dto){
        Room newRoom = roomService.saveRoom(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomMapper.toDto(newRoom));
    }

    @Operation(summary = "Busca sala/espaço por ID", description = "Retorna os detalhes de uma sala específica")
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getRoomId(@PathVariable Long id){
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(RoomMapper.toDto(room));
    }

    @Operation(summary = "Lista todas as salas/espaços", description = "Retorna uma lista completa de todas as salas cadastradas")
    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getAll(){
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(RoomMapper.toListDto(rooms));
    }

    @Operation(summary = "Atualiza uma sala/espaço", description = "Altera os dados de uma sala existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomCreateDto dto
    ) {
        Room roomUpdate = roomService.updateRoomById(id, dto);
        return ResponseEntity.ok(RoomMapper.toDto(roomUpdate));
    }

    @Operation(summary = "Exclui uma sala/espaço", description = "Remove uma sala do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.excludeRoom(id);
        return ResponseEntity.noContent().build();
    }
}