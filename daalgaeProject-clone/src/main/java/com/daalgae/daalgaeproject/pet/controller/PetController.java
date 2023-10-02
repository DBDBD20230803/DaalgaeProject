package com.daalgae.daalgaeproject.pet.controller;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.pet.model.dto.PetDTO;
import com.daalgae.daalgaeproject.pet.model.servie.PetService;
import com.daalgae.daalgaeproject.pet.model.servie.PetServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/pet")
public class PetController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PetService petService;



    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/insertPetInfo")
    public ResponseEntity<String> insertPet(@RequestBody PetDTO petDTO, Model model){

        try{
            log.info("===========반려견 정보: " + petDTO );
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            int memCode = 0;

            if (authentication.getPrincipal() instanceof UserImpl) {
                UserImpl user = (UserImpl) authentication.getPrincipal();
                memCode = user.getMemCode();
                petDTO.setRefMemCode(memCode);
            }

            petService.insertPet(petDTO);
            return ResponseEntity.ok("반려견 정보가 성공적으로 저장되었습니다.");

        }catch (Exception e){
            log.error("반려견 정보 저장 중 오류 발생 : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("반려견 정보 저장 중 오류가 발생했습니다.");
        }

    }

    @PostMapping("/updatePetInfo")
    public ResponseEntity<String> updatePet(@RequestBody PetDTO petDTO, Model model){
        log.info("===========updaetePetInfo==============");

        try{
            log.info("===========반려견 정보: " + petDTO );
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            int memCode = 0;

            if (authentication.getPrincipal() instanceof UserImpl) {
                UserImpl user = (UserImpl) authentication.getPrincipal();
                memCode = user.getMemCode();
                petDTO.setRefMemCode(memCode);
            }

            petService.updatePet(petDTO);
            return ResponseEntity.ok("반려견 정보가 성공적으로 수정되었습니다.");

        }catch (Exception e){
            log.error("반려견 정보 저장 중 오류 발생 : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("반려견 정보 수정 중 오류가 발생했습니다.");
        }

    }
}
