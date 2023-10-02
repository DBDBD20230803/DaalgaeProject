package com.daalgae.daalgaeproject.board.dto;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int postCode;
    private String postType;
    private String postSort;
    private String postTitle;
    private String postContent;
    private String attachThumbAddr;
    private Date postDate;
    private int postCount;
    private MemberDTO refPostWriter;
    private List<AttachmentDTO> attachmentList;
}
