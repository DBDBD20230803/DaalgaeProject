package com.daalgae.daalgaeproject.member.controller;

import com.daalgae.daalgaeproject.exception.member.EmailAuthException;
import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.service.LoginServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping(value ={ "/login", "/regist", "/myPage"})
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private  final PasswordEncoder passwordEncoder;
    private final LoginServiceImpl loginService;

    public MemberController(PasswordEncoder passwordEncoder, LoginServiceImpl loginService) {
        this.passwordEncoder = passwordEncoder;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login/login";
    }

    @PostMapping("/login")
    public String login(String id, MemberDTO memberDTO, Model model, Errors errors) throws EmailAuthException{

        if (errors.hasErrors()) {
            return "/login/login";
        }

        if (loginService.emailAuthFail(String.valueOf(memberDTO.getMailAuth())) != 1) {
            return "/regist/emailAuthFail";
        }

        return "redirect:/";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "login/loginSuccess";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/loginFindId")
    public String loginFindIdForm(){ return "login/loginFindId"; }

    @GetMapping("/loginFindPwd")
    public String loginFindFPwdForm(){ return "login/loginFindPwd"; }




    @GetMapping("/regist")
    public String registForm(){ return "regist/regist"; }

    @PostMapping("/regist")
    public String registMember(@ModelAttribute MemberDTO member, HttpServletRequest request) throws MemberRegistException, MessagingException, UnsupportedEncodingException {
        log.info("");
        log.info("");
        log.info("[MemberController] registMember ==========================================================");

        String memId = request.getParameter("username");
        member.setMemId(memId);

        String memPwd = request.getParameter("password");
        member.setMemPwd(memPwd);

        String memEmail = request.getParameter("putEmailAddress");
        member.setMemEmail(memEmail);

        String memName = request.getParameter("nameUser");
        member.setMemName(memName);

        String birthStr = request.getParameter("birth");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date memBirth = null;

        try {
            memBirth = dateFormat.parse(birthStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };

        member.setMemBirth(memBirth);


        String memAdrs = request.getParameter("address1");
        member.setMemAdrs(memAdrs);

        String memAdrsDetail = request.getParameter("address2");
        member.setMemAdrsDetail(memAdrsDetail);


        log.info("[MemberController] registMember request Member : " + member);


        int result = loginService.registMember(member);

        if(result == 1) {
            loginService.sendRegistEmail(member);
        }


        log.info("[MemberController] registMember ==========================================================");

        return "/regist/registComplete";

    }


    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO memberDTO) throws JsonProcessingException{

        log.info("");
        log.info("");
        log.info("[MemberController] checkDuplication =======================");

        String result = "사용 가능한 아이디 입니다.";
        log.info("[MemberController] Request Check ID : " + memberDTO.getMemId());

        if("".equals(memberDTO.getMemId())){
            log.info("[MemberController] No Input Member ID");
            result = "아이디를 입력해 주세요";
        }else if(loginService.selectMemberById(memberDTO.getMemId())){
            log.info("[MemberController] Already Exist");
            result = "중복된 아이디가 존재합니다.";
        }

        log.info("[MemberController] checkDuplication======================");

        return ResponseEntity.ok(result);

    }

    @GetMapping("/agreeTerms")
    public String agreeTerms(){ return "regist/agreeTerms"; }


    @GetMapping("/registComplete")
    public String registCompleteForm(){ return "regist/registComplete"; }

    @GetMapping( "/registEmailAuth")
    public String emailConfirm(MemberDTO memberDTO ) {
        loginService.updateMailAuth(memberDTO);
        System.out.println("확인!!!!!!!");

        return "regist/emailAuthSuccess";
    }


    @GetMapping("/mypage")
    public String mypageForm(){ return "myPage/mypage"; }



}