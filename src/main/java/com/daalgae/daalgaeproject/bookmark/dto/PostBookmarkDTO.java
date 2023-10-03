package com.daalgae.daalgaeproject.bookmark.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostBookmarkDTO {
    private int memCode;
    private int memSeq;
    private int postCode;
    public PostBookmarkDTO(int memCode, int postCode) {
        this.memCode = memCode;
        this.postCode = postCode;
    }
}
