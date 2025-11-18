package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.service.CampusService;
import com.example.ReservaIFPB_backend.web.dto.CampusCreateDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.CampusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/campus")
public class CampusController {

    private final CampusService campusService;

    @PostMapping
    public ResponseEntity<CampusResponseDto> createCampus(@RequestBody CampusCreateDto createDto){
        Campus newCampus = campusService.saveCampus(CampusMapper.toCampus(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CampusMapper.toDto(newCampus));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampusResponseDto> getCampusId(@PathVariable Long id){
        Campus campus = campusService.getCampusById(id);
        return ResponseEntity.ok(CampusMapper.toDto(campus));
    }

    @GetMapping
    public ResponseEntity<List<CampusResponseDto>> getAll(){
        List<Campus> campus = campusService.getAllCampus();
        return ResponseEntity.ok(CampusMapper.toListDto(campus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampusResponseDto> updateCampus(@PathVariable Long id, @RequestBody Campus campus){
        Campus campusUpdate = campusService.updateCampusById(id, campus.getName(), campus.getUf());
        return ResponseEntity.ok(CampusMapper.toDto(campusUpdate));
    }
}
