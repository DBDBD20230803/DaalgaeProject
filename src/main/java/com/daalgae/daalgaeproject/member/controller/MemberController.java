package com.daalgae.daalgaeproject.member.controller;

import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.service.LoginServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value ={ "/login", "/regist", "myPage"})
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

    @GetMapping("/loginFindId")
    public String loginFindIdForm(){ return "login/loginFindId"; }

    @GetMapping("/loginFindPwd")
    public String loginFindFPwdForm(){ return "login/loginFindPwd"; }




    @GetMapping("/regist")
    public String registForm(){ return "regist/regist"; }

    @PostMapping("/regist")
    public String registMember(@ModelAttribute MemberDTO member, HttpServletRequest request, RedirectAttributes rttr) throws MemberRegistException {
        log.info("");
        log.info("");
        log.info("[MemberController] registMember ==========================================================");

        String address = request.getParameter("address") + "$" + request.getParameter("address1") + "$" + request.getParameter("address2");
        member.setMemAdrs(address);
        member.setMemPwd(passwordEncoder.encode(member.getMemPwd()));

        log.info("[MemberController] registMember request Member : " + member);

        loginService.registMember(member);

        rttr.addFlashAttribute("message", "회원 가입에 성공했습니다!");

        log.info("[MemberController] registMember ==========================================================");

        return "redirect:/regist/registComplete";

    }


    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication(MemberDTO memberDTO) throws JsonProcessingException{

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





    @GetMapping("/mypage")
    public String mypageForm(){ return "myPage/mypage"; }
}