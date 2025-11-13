package com.example.ReservaIFPB_backend.web.dto.mapper;

import com.example.ReservaIFPB_backend.entity.Block;
import com.example.ReservaIFPB_backend.web.dto.BlockCreateDto;
import com.example.ReservaIFPB_backend.web.dto.BlockResponseDto;
import org.modelmapper.ModelMapper;

public class BlockMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Block toBlock(BlockCreateDto createDto){
        return modelMapper.map(createDto, Block.class);
    }

    public static BlockResponseDto toDto(Block block){
        return modelMapper.map(block, BlockResponseDto.class);
    }
}
