package com.daalgae.daalgaeproject.member.model.dao;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {


    MemberDTO findMemberById(String memberId);


    int insertMember(MemberDTO member);

    String selectMemberById(String memId);

    int updateMailKey(MemberDTO member);


    int updateMailAuth(MemberDTO member);

    int emailAuthFail(String id);
}
