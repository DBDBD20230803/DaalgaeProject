package com.daalgae.daalgaeproject.bookmark.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TourBookmarkDTO {
    private int memCode;
    private int memSeq;
    private int tourCode;
    public TourBookmarkDTO(int memCode, int tourCode) {
        this.memCode = memCode;
        this.tourCode = tourCode;
    }
}
