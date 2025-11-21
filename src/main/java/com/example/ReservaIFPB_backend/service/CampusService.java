package com.example.ReservaIFPB_backend.service;

import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.exception.CampusAlreadyExistsException;
import com.example.ReservaIFPB_backend.exception.CampusNotFoundException;
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
        boolean exists = campusRepository.existsByNameIgnoreCase(campus.getName());

        if (exists) {
            throw new CampusAlreadyExistsException(campus.getName());
        }

        return campusRepository.save(campus);
    }

    @Transactional(readOnly = true)
    public Campus getCampusById(Long id){
        return campusRepository.findById(id)
                .orElseThrow(() -> new CampusNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Campus> getAllCampus(){
        return campusRepository.findAll();
    }

    @Transactional
    public Campus updateCampusById(Long id, String name, String uf){
        Campus campus = getCampusById(id);
        campus.setName(name);
        campus.setUf(uf);
        return campus;
    }

    @Transactional
    public void excludeCampus(Long id){
        Campus campus = getCampusById(id);
        campusRepository.deleteById(id);
    }
}
