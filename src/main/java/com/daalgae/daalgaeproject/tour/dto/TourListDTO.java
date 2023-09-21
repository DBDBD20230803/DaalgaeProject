package com.daalgae.daalgaeproject.tour.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TourListDTO {
    private int tourCode;
    private String tourPhoto;
    private String tourTitle;
    private String addr;
    private String tel;
}
