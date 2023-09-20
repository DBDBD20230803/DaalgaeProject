package com.daalgae.daalgaeproject.member.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO {

    private int authCode;
    private String authName;
    private List<AuthenticatedMenuDTO> authenticatedMenuDTOList;

}
