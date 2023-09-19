package com.daalgae.daalgaeproject.member.model.service;

import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.mail.MailHandler;
import com.daalgae.daalgaeproject.member.mail.TempKey;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.AuthorityDTO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.MemberRoleDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.beans.JavaBean;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;



@Service
public class LoginServiceImpl implements LoginService {

    private MemberDAO memberDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final PasswordEncoder passwordEncoder;

    private JavaMailSender mailSender;

    @Autowired
    public LoginServiceImpl(MemberDAO memberDAO, PasswordEncoder passwordEncoder, JavaMailSender mailSender){
        this.memberDAO = memberDAO;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
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
    public void registMember(MemberDTO member) throws MemberRegistException, MessagingException {

        String mail_key = new TempKey().getKey(30, false);
        member.setMailKey(mail_key);

        String encPassword = passwordEncoder.encode(member.getMemPwd());
        member.setMemPwd(encPassword);

        log.info("[MemberService] Insert Member : " + member);
        int result = memberDAO.insertMember(member);
        memberDAO.updateMailKey(member);


        log.info("[MemberService] Insert result : " + ((result > 0) ? "회원가입 성공" : "회원가입 실패"));


        if(!(result > 0)){
            throw new MemberRegistException("회원 가입에 실패했습니다.");
        }
    }


    public void sendRegistEmail(MemberDTO member) throws MessagingException, UnsupportedEncodingException {
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[뭐든다알개] 이메일 인증메일 입니다.");
        sendMail.setText(
                "<h1>뭐든다알개 메일인증</h1>" +
                "<br>뭐든다알개에 오신 것을 환영합니다!" +
                "<br>아래 [이메일 인증 확인]을 눌러주세요." +
                "<br><a href='http://localhost:8001/regist/registEmailAuth?email=" + member.getMemEmail() +
                "&mail_key=" + member.getMailKey() +
                "' target='_blank'>이메일 인증 확인</a>");
        sendMail.setFrom("daalgae@naver.com", "뭐든다알개");
        sendMail.setTo(member.getMemEmail());
        sendMail.send();

        log.info("회원가입 인증 메일 발송 성공");
    }


    public boolean selectMemberById(String memId) {
        String result = memberDAO.selectMemberById(memId);

        return result != null? true : false;
    }

    @Override
    public int updateMailKey(MemberDTO memberDTO) {
        return memberDAO.updateMailKey(memberDTO);
    }

    @Override
    public int updateMailAuth(MemberDTO memberDTO) {
        return memberDAO.updateMailAuth(memberDTO);
    }

    @Override
    public int emailAuthFail(String id) {
        return memberDAO.emailAuthFail(id);
    }
}


