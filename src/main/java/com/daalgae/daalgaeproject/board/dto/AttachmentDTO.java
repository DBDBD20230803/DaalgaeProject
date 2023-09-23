package com.daalgae.daalgaeproject.board.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDTO {
    private int attachCode;
    private String attachOriginName;
    private String attachDbName;
    private String attachThumbAddr;
    private String attachOriginAddr;
    private int refPostCode;

}