package com.daalgae.daalgaeproject.tour.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TourDTO {
    private int tourCode;
    private String tourPhoto;
    private String tourTitle;
    private String addr;
    private String tel;
    private String mapx;
    private int mapy;
    private String tourCategory;
    private String time;
    private String page;
    private String facilities;
    private String supplies;
    private String policy;
    private String notice;
    private String intro;
}
