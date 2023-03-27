package com.boot3.service;


import com.boot3.data.dto.request.FruitReqDTO;
import com.boot3.data.dto.response.FruitDTO;
import com.boot3.data.entity.FruitEntity;
import com.boot3.data.mapstruct.FruitMapper;
import com.boot3.data.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitDTO save(String name) {
        FruitEntity data = FruitEntity.builder().name(name).build();
        fruitRepository.save(data);

        return getFruit(FruitReqDTO.builder().name(name).build());
    }

    public FruitDTO getFruit(FruitReqDTO dto) {
        return FruitMapper.INSTANCE.toDto(fruitRepository.findByName(dto.getName()));
    }

    public List<FruitDTO> getFruits() {
        return FruitMapper.INSTANCE.toDtoList(fruitRepository.findAll());
    }
}
