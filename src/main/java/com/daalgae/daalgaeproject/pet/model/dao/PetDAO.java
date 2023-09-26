package com.daalgae.daalgaeproject.pet.model.dao;

import com.daalgae.daalgaeproject.pet.model.dto.PetDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface PetDAO {
    int insertPet(PetDTO petDTO);

    List<PetDTO> getPetInfoByMemCode(int memCode);
}

