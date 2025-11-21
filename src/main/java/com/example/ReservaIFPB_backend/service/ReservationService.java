package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Reservation;
import com.example.ReservaIFPB_backend.entity.Room;
import com.example.ReservaIFPB_backend.entity.User;
import com.example.ReservaIFPB_backend.exception.ReservationNotFoundException;
import com.example.ReservaIFPB_backend.repository.ReservationRepository;
import com.example.ReservaIFPB_backend.web.dto.ReservationCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final RoomService roomService;

    @Transactional
    public Reservation saveReservation(ReservationCreateDto dto){
        User teacher = userService.getUserById(dto.getTeacherId());
        Room room = roomService.getRoomById(dto.getRoomId());


        Reservation reservation = new Reservation();
        reservation.setTeacher(teacher);
        reservation.setRoom(room);
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setDescription(dto.getDescription());
        reservation.setActivity(dto.getActivity());

        return reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public Reservation getReservationById(Long id){
        return reservationRepository.findById(id).orElseThrow(
                () -> new ReservationNotFoundException("Reservation not found with id: " + id)
        );
    }

    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByTeacherId(Long teacherId){
        return reservationRepository.findByTeacherId(teacherId);
    }

    @Transactional
    public Reservation updateReservationById(Long id, ReservationCreateDto dto) {
        Reservation reservation = getReservationById(id);

        if (reservation.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível alterar uma reserva que já começou!");
        }

        User teacher = userService.getUserById(dto.getTeacherId());
        Room room = roomService.getRoomById(dto.getRoomId());

        reservation.setTeacher(teacher);
        reservation.setRoom(room);
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setDescription(dto.getDescription());
        reservation.setActivity(dto.getActivity());

        return reservation;
    }

    @Transactional
    public void excludeReservation(Long id){
        Reservation reservation = getReservationById(id);

        if (reservation.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível excluir uma reserva que já começou!");
        }

        reservationRepository.deleteById(id);
    }
}