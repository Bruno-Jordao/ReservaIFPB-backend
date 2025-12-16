package com.example.ReservaIFPB_backend.web.controller;

import com.example.ReservaIFPB_backend.entity.Campus;
import com.example.ReservaIFPB_backend.service.CampusService;
import com.example.ReservaIFPB_backend.web.dto.CampusCreateDto;
import com.example.ReservaIFPB_backend.web.dto.CampusResponseDto;
import com.example.ReservaIFPB_backend.web.dto.mapper.CampusMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Campus", description = "Endpoints para gerenciamento de Campus do IFPB")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/campus")
public class CampusController {

    private final CampusService campusService;

    @Operation(summary = "Cria um novo campus", description = "Registra um novo campus na base de dados")
    @PostMapping
    public ResponseEntity<CampusResponseDto> createCampus(@Valid @RequestBody CampusCreateDto createDto){
        Campus newCampus = campusService.saveCampus(CampusMapper.toCampus(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CampusMapper.toDto(newCampus));
    }

    @Operation(summary = "Busca campus por ID", description = "Retorna os detalhes de um campus específico")
    @GetMapping("/{id}")
    public ResponseEntity<CampusResponseDto> getCampusId(@PathVariable Long id){
        Campus campus = campusService.getCampusById(id);
        return ResponseEntity.ok(CampusMapper.toDto(campus));
    }

    @Operation(summary = "Lista todos os campus", description = "Retorna uma lista completa de todos os campus cadastrados")
    @GetMapping
    public ResponseEntity<List<CampusResponseDto>> getAll(){
        List<Campus> campus = campusService.getAllCampus();
        return ResponseEntity.ok(CampusMapper.toListDto(campus));
    }

    @Operation(summary = "Atualiza um campus", description = "Altera o nome e UF de um campus existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<CampusResponseDto> updateCampus(@PathVariable Long id, @Valid @RequestBody CampusCreateDto campus){
        Campus campusUpdate = campusService.updateCampusById(id, campus.getName(), campus.getUf());
        return ResponseEntity.ok(CampusMapper.toDto(campusUpdate));
    }

    @Operation(summary = "Exclui um campus", description = "Remove um campus do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampus(@PathVariable Long id){
        campusService.excludeCampus(id);
        return ResponseEntity.noContent().build();
    }
}