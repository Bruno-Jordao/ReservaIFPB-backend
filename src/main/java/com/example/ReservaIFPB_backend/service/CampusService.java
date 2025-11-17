package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.repository.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CampusService {

    private final CampusRepository campusRepository;

    @Transactional
    public Campus saveCampus(Campus campus){
        return campusRepository.save(campus);
    }

    @Transactional(readOnly = true)
    public Campus getCampusById(Long id){
        return campusRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Campus not found!")
        );
    }

    @Transactional(readOnly = true)
    public List<Campus> getAllCampus(){
        return campusRepository.findAll();
    }
}
