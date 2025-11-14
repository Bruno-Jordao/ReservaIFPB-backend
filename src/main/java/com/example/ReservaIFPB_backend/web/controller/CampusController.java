package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.service.CampusService;
import com.example.ReservaIFPB_backend.web.dto.CampusCreateDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.CampusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
