package com.daalgae.daalgaeproject.member.model.dao;

import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;

public interface MemberDAO {
    MemberDTO findMemberById(String memberId);
}
