/*
package com.daalgae.daalgaeproject.login.model.service;

import com.daalgae.daalgaeproject.login.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.login.model.dto.AuthorityDTO;
import com.daalgae.daalgaeproject.login.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.login.model.dto.MemberRoleDTO;
import com.daalgae.daalgaeproject.login.model.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    private MemberDAO memberDAO;

    @Autowired
    public LoginServiceImpl(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
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
}
*/
