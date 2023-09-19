
package com.daalgae.daalgaeproject.member.model.service;

import com.daalgae.daalgaeproject.exception.member.EmailAuthException;
import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
public interface LoginService extends UserDetailsService {

    public void registMember(MemberDTO member) throws MemberRegistException, MessagingException;

    int updateMailKey(MemberDTO memberDTO) throws EmailAuthException, MessagingException;
    int updateMailAuth(MemberDTO memberDTO) throws EmailAuthException, MessagingException;
    int emailAuthFail(String id) throws EmailAuthException, MessagingException;

}


