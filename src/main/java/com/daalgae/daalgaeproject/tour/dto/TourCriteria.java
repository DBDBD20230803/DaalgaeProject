package com.daalgae.daalgaeproject.tour.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TourCriteria {
    private String no;
    private String keyword;
    private String country;
    private String category;

    public TourCriteria(String no, String keyword, String country) {
        this.no = no;
        this.keyword = keyword;
        this.country = country;
    }
}
