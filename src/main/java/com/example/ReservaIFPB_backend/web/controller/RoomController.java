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

@Tag(name = "Rooms", description = "Endpoints for managing Rooms/Spaces")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Create a new room/space", description = "Registers a new room that belongs to a block")
    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@Valid @RequestBody RoomCreateDto dto){
        Room newRoom = roomService.saveRoom(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomMapper.toDto(newRoom));
    }

    @Operation(summary = "Search for room/space by ID", description = "Returns the details of a specific room")
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getRoomId(@PathVariable Long id){
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(RoomMapper.toDto(room));
    }

    @Operation(summary = "List all rooms/spaces", description = "Returns a complete list of all registered rooms")
    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getAll(){
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(RoomMapper.toListDto(rooms));
    }

    @Operation(summary = "Update a room/space by ID", description = "Changes the data of an existing room by ID")
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody RoomCreateDto dto
    ) {
        Room roomUpdate = roomService.updateRoomById(id, dto);
        return ResponseEntity.ok(RoomMapper.toDto(roomUpdate));
    }

    @Operation(summary = "Delete a room/space", description = "Remove a room from the system by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.excludeRoom(id);
        return ResponseEntity.noContent().build();
    }
}