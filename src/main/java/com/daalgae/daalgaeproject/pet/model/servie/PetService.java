package com.daalgae.daalgaeproject.pet.model.servie;

import com.daalgae.daalgaeproject.pet.model.dao.PetDAO;
import com.daalgae.daalgaeproject.pet.model.dto.PetDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {

        public int insertPet(PetDTO petDTO);

        public List<PetDTO> getPetInfoByMemCode(int memCode);

        public int updatePet(PetDTO petDTO);
}