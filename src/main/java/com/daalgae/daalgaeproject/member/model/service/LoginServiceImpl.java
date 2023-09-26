package com.daalgae.daalgaeproject.member.model.service;

import com.daalgae.daalgaeproject.exception.member.MemberModifyException;
import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.mail.MailHandler;
import com.daalgae.daalgaeproject.member.mail.TempKey;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.*;


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

    @Transactional
    public int login(MemberDTO memberDTO) throws UsernameNotFoundException{

        String rawPassword = memberDTO.getMemPwd();
        String encodedPassword = memberDAO.getEncPass(memberDTO.getMemId());

        if(!passwordEncoder.matches(rawPassword, encodedPassword)){
            return 0;
        }else{
            memberDTO.setMemPwd(encodedPassword);
            return memberDAO.login(memberDTO);
        }
    }

    @Override
    public UserDetails loadUserByUsername(@RequestParam("username") String memId) throws UsernameNotFoundException {

        log.info("[LoginService] ========================");
        log.info("[LoginService] memId : " + memId);

        MemberDTO member = memberDAO.findMemberById(memId);

        if(member == null){
            member = new MemberDTO();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(member.getMemRole() != null) {
            String roleList = member.getMemRole();

            authorities.add(new SimpleGrantedAuthority(roleList));
        }

        boolean mailAuth = member.getMailAuth() == 1;
        log.info("membId : " + memId);

        UserImpl user = new UserImpl(member.getMemId(), member.getMemPwd(), mailAuth, authorities);
        user.setDetails(member);
        return user;
    }


    @Transactional
    public int registMember(MemberDTO member) throws MemberRegistException, MessagingException {

        String mailKey = new TempKey().getKey(30, false);
        member.setMailKey(mailKey);

        String encPassword = passwordEncoder.encode(member.getMemPwd());
        member.setMemPwd(encPassword);

        log.info("[MemberService] Insert Member : " + member);
        int result = memberDAO.registMember(member);
        memberDAO.updateMailKey(member);

        log.info("[MemberService] Insert result : " + ((result > 0) ? "회원가입 성공" : "회원가입 실패"));

        if(!(result > 0)){
            throw new MemberRegistException("회원 가입에 실패했습니다.");
        }
        return result;
    }


    @Transactional
    public void sendRegistEmail(MemberDTO member) throws MessagingException, UnsupportedEncodingException {
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[뭐든다알개] 이메일 인증메일 입니다.");
        sendMail.setText(
                "<h1>뭐든다알개 메일인증</h1>" +
                        "<br>뭐든다알개에 오신 것을 환영합니다!" +
                        "<br>아래 [이메일 인증 확인]을 눌러주세요." +
                        "<br><a href='http://localhost:8001/regist/registEmailAuth?memEmail=" + member.getMemEmail() +
                        "&mailKey=" + member.getMailKey() +
                        "' target='_blank'>이메일 인증 확인</a>");
        sendMail.setFrom("daalgae@naver.com", "뭐든다알개");
        sendMail.setTo(member.getMemEmail());
        sendMail.send();


        log.info("회원가입 인증 메일 발송 성공");


    }


    @Transactional
    public boolean selectMemberById(String memId) {
        String result = memberDAO.selectMemberById(memId);


        return result != null? true : false;
    }

    @Transactional
    public int updateMailKey(MemberDTO memberDTO) {
        return memberDAO.updateMailKey(memberDTO);
    }

    @Transactional
    public int updateMailAuth(MemberDTO memberDTO) {
        return memberDAO.updateMailAuth(memberDTO);
    }

    @Transactional
    public int emailAuthFail(String id) {
        return memberDAO.emailAuthFail(id);
    }

    @Transactional
    public List<MemberDTO> findId(MemberDTO memberDTO) {
        log.info("memberDTO : " + memberDTO + "왜안나와!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return memberDAO.findId(memberDTO);
    }

    @Transactional
    public int getFindUserResult(MemberDTO memberDTO) {
        return memberDAO.getFindUserResult(memberDTO);
    }

    @Transactional
    public void findPass(MemberDTO memberDTO) throws MessagingException, UnsupportedEncodingException {
        log.info("MemberServiceImpl.findPass ==================");

        if(getFindUserResult(memberDTO) == 1){

            String pass = new TempKey().getKey(15, false);

            String encPassword = passwordEncoder.encode(pass);

            memberDTO.setMemPwd(encPassword);
            memberDAO.updateRandomPass(memberDTO);

            MailHandler sendMail = new MailHandler(mailSender);
            sendMail.setSubject("[뭐든다알개 임시비밀번호 입니다.]");
            sendMail.setText(
                    "<h1>뭐든 다알개 임시비밀번호</h1>" +
                            "<br>회원님의 임시비밀번호입니다." +
                            "<br><b>" + pass + "</b>" +
                            "<br>로그인 후 반드시 비밀번호를 변경해주세요!!");
            sendMail.setFrom("daalgae@naver.com", "뭐든다알개");
            sendMail.setTo(memberDTO.getMemEmail());
            sendMail.send();
            log.info("비밀번호 찾기 메일 발송 성공!");
        }
    }

    @Transactional
    public MemberDTO mypageRead(String memId){
        log.info("작동 되는거 맞니?????????????????????");
        log.info("memId : " + memId);
        return memberDAO.mypageRead(memId);
    }


    @Transactional
    public void modifyMember(MemberDTO memberDTO) throws MemberModifyException{
        System.out.println("회원 수정 정보 넘겨주니 ?? : " + memberDTO);
        int result = memberDAO.modifyMember(memberDTO);

        if(!(result > 0)) {
            throw new MemberModifyException("회원 정보 수정에 실패하셨습니다.");
        }
    }
}


