package com.daalgae.daalgaeproject.board.dto;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private int replyCode;
    private String replyCon;
    private Date replyDate;
    private int refReplyWriter;
    private int refPostCode;
    private MemberDTO replyWriterDetail;

}