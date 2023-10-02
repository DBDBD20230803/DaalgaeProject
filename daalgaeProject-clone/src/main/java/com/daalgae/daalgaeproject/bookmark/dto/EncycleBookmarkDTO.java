package com.daalgae.daalgaeproject.bookmark.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EncycleBookmarkDTO {
    private int memCode;
    private int memSeq;
    private int EncycleCode;

    public EncycleBookmarkDTO(int memCode, int encycleCode) {
        this.memCode = memCode;
        EncycleCode = encycleCode;
    }
}
