package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.service.RoomService;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import com.example.ReservaIFPB_backend.web.dto.RoomResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody RoomCreateDto dto){
        Room newRoom = roomService.saveRoom(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomMapper.toDto(newRoom));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> getRoomId(@PathVariable Long id){
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(RoomMapper.toDto(room));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> getAll(){
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(RoomMapper.toListDto(rooms));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> updateRoom(
            @PathVariable Long id,
            @RequestBody RoomCreateDto dto
    ) {
        Room roomUpdate = roomService.updateRoomById(id, dto);
        return ResponseEntity.ok(RoomMapper.toDto(roomUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.excludeRoom(id);
        return ResponseEntity.noContent().build();
    }
}
