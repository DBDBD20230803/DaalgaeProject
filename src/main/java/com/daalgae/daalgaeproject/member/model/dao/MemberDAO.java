package com.daalgae.daalgaeproject.member.model.dao;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Mapper
public interface MemberDAO {


    MemberDTO findMemberById(String memberId);


    int registMember(MemberDTO member);

    String selectMemberById(String memId);

    int updateMailKey(MemberDTO member);


    int updateMailAuth(MemberDTO member);

    int emailAuthFail(String id);

    String getEncPass(String memId);

    int login(MemberDTO memberDTO);
}
