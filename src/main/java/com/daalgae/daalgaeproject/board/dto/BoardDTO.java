package com.daalgae.daalgaeproject.board.dto;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int postCode;
    private String postSort;
    private String postTitle;
    private String postContent;
    private Date postDate;
    private MemberDTO memCode;

}
