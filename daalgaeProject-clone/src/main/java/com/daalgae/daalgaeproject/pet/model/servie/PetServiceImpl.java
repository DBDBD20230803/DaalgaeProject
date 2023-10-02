package com.daalgae.daalgaeproject.pet.model.servie;

import com.daalgae.daalgaeproject.pet.model.dao.PetDAO;
import com.daalgae.daalgaeproject.pet.model.dto.PetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetDAO petDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public PetServiceImpl(PetDAO petDAO) {
        this.petDAO = petDAO;

    }


    @Transactional
    public int insertPet(PetDTO petDTO) {
        log.info("PetServiceImple petDTO : " + petDTO);
        return petDAO.insertPet(petDTO);
    }

    @Transactional
    public List<PetDTO> getPetInfoByMemCode(int memCode) {
        return petDAO.getPetInfoByMemCode(memCode);
    }

    @Transactional
    public int updatePet(PetDTO petDTO){
        return petDAO.updatePet(petDTO);
    };
}
