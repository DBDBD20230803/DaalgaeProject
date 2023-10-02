package com.daalgae.daalgaeproject.mainConnect.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MainNoticeDTO {
    private int postCode;
    private String postTitle;
    private String postSort;
    private String postDate;
}
