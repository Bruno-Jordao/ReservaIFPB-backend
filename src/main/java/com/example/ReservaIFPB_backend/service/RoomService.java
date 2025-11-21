package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.exception.RoomAlreadyExistsException;
import com.example.ReservaIFPB_backend.exception.RoomNotFoundException;
import com.example.ReservaIFPB_backend.repository.RoomRepository;
import com.example.ReservaIFPB_backend.web.dto.RoomCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final BlockService blockService;

    @Transactional
    public Room saveRoom(RoomCreateDto dto){
        Block block = blockService.getBlockById(dto.getBlockId());

        boolean exists = roomRepository.existsByNameIgnoreCaseAndBlockId(dto.getName(), dto.getBlockId());
        if (exists) {
            throw new RoomAlreadyExistsException("Room already exists in this block");
        }

        Room room = new Room();
        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());
        room.setBlock(block);
        room.setFloor(dto.getFloor());

        return roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(
                () -> new RoomNotFoundException("Room not found with id: " + id)
        );
    }

    @Transactional(readOnly = true)
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @Transactional
    public Room updateRoomById(Long id, RoomCreateDto dto) {
        Room room = getRoomById(id);

        Block block = blockService.getBlockById(dto.getBlockId());

        room.setName(dto.getName());
        room.setCapacity(dto.getCapacity());
        room.setBlock(block);
        room.setFloor(dto.getFloor());

        return room;
    }

    @Transactional
    public void excludeRoom(Long id){
        Room room = getRoomById(id);
        roomRepository.deleteById(id);
    }
}
