
package com.daalgae.daalgaeproject.member.model.service;

import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface LoginService extends UserDetailsService {

    public void registMember(MemberDTO member) throws MemberRegistException;
}


