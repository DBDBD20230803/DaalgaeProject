
package com.daalgae.daalgaeproject.member.model.service;

import com.daalgae.daalgaeproject.exception.member.EmailAuthException;
import com.daalgae.daalgaeproject.exception.member.MemberModifyException;
import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public interface LoginService extends UserDetailsService {

    public int registMember(MemberDTO member) throws MemberRegistException, MessagingException;

    public boolean selectMemberById(String memId) throws MessagingException;

    int updateMailAuth(MemberDTO memberDTO) throws EmailAuthException, MessagingException;
    int emailAuthFail(String id) throws EmailAuthException, MessagingException;

    List<MemberDTO> findId(MemberDTO memberDTO) throws Exception;

    int getFindUserResult(MemberDTO memberDTO) throws Exception;

    void findPass(MemberDTO memberDTO) throws Exception;

    MemberDTO mypageRead(String memId) throws Exception;

    void modifyMember(MemberDTO memberDTO) throws MemberModifyException;

    void modifyPwd(MemberDTO memberDTO) throws MemberModifyException;

    void memberDelete(MemberDTO memberDTO) throws MemberModifyException;
}


