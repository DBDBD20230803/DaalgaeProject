package com.daalgae.daalgaeproject.login.model.dao;

import com.daalgae.daalgaeproject.login.model.dto.MemberDTO;

public interface MemberDAO {
    MemberDTO findMemberById(String memberId);
}
