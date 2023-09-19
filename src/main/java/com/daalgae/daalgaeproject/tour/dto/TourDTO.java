package com.daalgae.daalgaeproject.tour.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TourDTO {
    private int tourCode;
    private String tourTitle;
    private String addr;
    private String tel;
    private String time;
    private String page;
    private String intro;
    private String coordinate;
}
