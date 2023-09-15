package com.daalgae.daalgaeproject.member.model.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedMenuDTO {
    private int authCode;
    private int menuCode;
    private GlobalMenuDTO globalMenu;

}
