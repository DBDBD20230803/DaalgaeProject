package com.daalgae.daalgaeproject.member.model.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoleDTO {

    private int memCode;
    private int authCode;
    private AuthorityDTO authority;
}
