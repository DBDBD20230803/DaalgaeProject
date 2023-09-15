package com.daalgae.daalgaeproject.member.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GlobalMenuDTO {
    private int menuCode;
    private String menuName;
    private String menuUrl;
    private int type;
    private Integer refMenuCode;
}
