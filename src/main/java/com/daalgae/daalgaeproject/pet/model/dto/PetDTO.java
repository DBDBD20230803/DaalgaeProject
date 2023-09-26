package com.daalgae.daalgaeproject.pet.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    private int petCode;
    private String petNick;
    private String petKind;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date petBirth;


    private String petGender;
    private float petWeight;
    private String petNeutered;
    private String petIntroduce;
    private int refMemCode;
}
