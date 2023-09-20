package com.daalgae.daalgaeproject.member.model.service;

import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.AuthorityDTO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.MemberRoleDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
public class LoginServiceImpl implements LoginService {

    private MemberDAO memberDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImpl(MemberDAO memberDAO, PasswordEncoder passwordEncoder){
        this.memberDAO = memberDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        MemberDTO member = memberDAO.findMemberById(memberId);

        if(member == null){
            member = new MemberDTO();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(member.getMemberRoleList() != null){
            List<MemberRoleDTO> roleList = member.getMemberRoleList();

            for(int i = 0; i < roleList.size(); i++){
                AuthorityDTO authority = roleList.get(i).getAuthority();
                authorities.add(new SimpleGrantedAuthority(authority.getAuthName()));
            }
        }


        UserImpl user = new UserImpl(member.getMemId(), member.getMemPwd(), authorities);
        user.setDetails(member);

        return user;
    }


    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException{

        log.info("[MemberService] Insert Member : " + member);
        int result = memberDAO.insertMember(member);

        log.info("[MemberService] Insert result : " + ((result > 0) ? "회원가입 성공" : "회원가입 실패"));

        if(!(result > 0)){
            throw new MemberRegistException("회원 가입에 실패했습니다.");
        }
    }

    public boolean selectMemberById(String memId) {
        String result = memberDAO.selectMemberId(memId);

        return result != null? true : false;
    }
}


