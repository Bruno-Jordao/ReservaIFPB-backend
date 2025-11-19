package com.example.ReservaIFPB_backend.repository;

import com.example.ReservaIFPB_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
