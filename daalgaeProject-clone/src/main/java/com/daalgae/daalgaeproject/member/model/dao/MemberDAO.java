package com.daalgae.daalgaeproject.member.model.dao;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface MemberDAO {

    MemberDTO findMemberById(String memberId);


    int registMember(MemberDTO member);

    String selectMemberById(String memId);

    int updateMailKey(MemberDTO member);


    int updateMailAuth(MemberDTO member);

    int emailAuthFail(String id);

    String getEncPass(String memId);

    int login(MemberDTO memberDTO);


    // MemDogGum 업데이트를 위해 작성하였습니다 --선호
    List<MemberDTO> memDogGum(Map<String, List<Integer>> memCode);

    int updateMemDogGum(UserImpl member);

    int getMemberByMemCode (int memCode);

    List<MemberDTO> findId(MemberDTO memberDTO);

    int getFindUserResult(MemberDTO memberDTO);

    int updateRandomPass(MemberDTO memberDTO);

    MemberDTO mypageRead(String memId);

    int modifyMember(MemberDTO memberDTO);

    int modifyPwd(MemberDTO memberDTO);

    int memberDelete(MemberDTO memberDTO);

}

