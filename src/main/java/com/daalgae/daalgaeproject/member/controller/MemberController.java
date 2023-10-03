package com.daalgae.daalgaeproject.member.controller;

import com.daalgae.daalgaeproject.common.exception.thumbnail.ThumbnailRegistException;
import com.daalgae.daalgaeproject.common.util.SessionUtil;
import com.daalgae.daalgaeproject.exception.member.MemberModifyException;
import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.member.model.service.LoginServiceImpl;
import com.daalgae.daalgaeproject.pet.model.dto.PetDTO;
import com.daalgae.daalgaeproject.pet.model.servie.PetServiceImpl;
import com.daalgae.daalgaeproject.userProfile.model.dto.UserProfileDTO;
import com.daalgae.daalgaeproject.userProfile.model.service.UserProfileServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value ={ "/login", "/regist", "/myPage"})
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private  final PasswordEncoder passwordEncoder;
    private final LoginServiceImpl loginService;
    private final PetServiceImpl petService;
    private final UserProfileServiceImpl userProfileServiceImpl;


    public MemberController(PasswordEncoder passwordEncoder, LoginServiceImpl loginService, PetServiceImpl petService, UserProfileServiceImpl userProfileServiceImple) {
        this.passwordEncoder = passwordEncoder;
        this.loginService = loginService;
        this.petService = petService;
        this.userProfileServiceImpl = userProfileServiceImple;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login/login";
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, Principal principal){
        if(principal != null){
            String memId = principal.getName();
            model.addAttribute("memId", memId);
        }
        return "login/loginSuccess";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/loginFindId")
    public String loginFindIdForm(){ return "login/loginFindId"; }

    @PostMapping("/loginFindId")
    public String loginFindId(MemberDTO memberDTO, Model model){

        List<MemberDTO> idList = loginService.findId(memberDTO);

        List<String> memIds = new ArrayList<>();
        for (MemberDTO dto : idList) {
            String memId = dto.getMemId();
            memIds.add(memId);
        }
        model.addAttribute("memIds", memIds);

        log.info("idList : " + idList + "드디어 나왔쥬~?");
        return "login/FindIdComplete";
    }

    @GetMapping("/loginFindPwd")
    public String loginFindFPwdForm(){ return "login/loginFindPwd"; }


    @PostMapping("/loginFindPwd")
    public String findPass(MemberDTO memberDTO, Model model) throws MessagingException, UnsupportedEncodingException {
       int result = loginService.getFindUserResult(memberDTO);
       log.info("MemberController.findPassword : result={}", result);
       model.addAttribute("result", result);

       loginService.findPass(memberDTO);
       return "/login/FindPwdComplete";
    }



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

        String memEmail = request.getParameter("memEmail");
        member.setMemEmail(memEmail);

        String memName = request.getParameter("memName");
        member.setMemName(memName);
        log.info("memName:" + memName);

        String birthStr = request.getParameter("memBirth");
        System.out.println("birthStr : " + birthStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date memBirth = null;
        log.info("memBirth:" + memBirth);


        try {
            if(birthStr != null && !birthStr.isEmpty()){
                memBirth = dateFormat.parse(birthStr);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        log.info("memBirth:" + memBirth);
        member.setMemBirth(memBirth);


        String memAdrs = request.getParameter("memAdrs");
        log.info("memAdrs:" + memAdrs);
        member.setMemAdrs(memAdrs);

        String memAdrsDetail = request.getParameter("memAdrsDetail");
        log.info("memAdrsDetail:" + memAdrsDetail);
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
    public String mypageForm(Principal principal, Model model, PetDTO petDTO, UserProfileDTO userProfileDTO, HttpServletRequest request) throws ThumbnailRegistException {
        log.info("마이페이지로 이동!!!!!!!!!!!!!");
        log.info("유저아이디 : " + principal.getName());

        /*회원정보*/
        String memId = principal.getName();
        MemberDTO memberDTO = loginService.mypageRead(memId);
        model.addAttribute("member", memberDTO);
        log.info("memberDTO : " + memberDTO);




        /*반려견 정보*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        int memCode = 0;
        if (authentication.getPrincipal() instanceof UserImpl) {

            UserImpl user = (UserImpl) authentication.getPrincipal();
            memCode = user.getMemCode();
            petDTO.setRefMemCode(memCode);
        }
        List<PetDTO> petList = petService.getPetInfoByMemCode(memCode);
        model.addAttribute("petList", petList);
        log.info("memCode : " + memCode);
        log.info("petList : " + petList);


        try {
            /*프로필사진*/
            if (authentication.getPrincipal() instanceof UserImpl) {
                UserImpl user = (UserImpl) authentication.getPrincipal();
                memCode = user.getMemCode();
                userProfileDTO.setRefMemCode(memCode);


                List<UserProfileDTO> userProfileDTOList = userProfileServiceImpl.selectThumbnail(memCode);


                model.addAttribute("thumbnail", userProfileDTOList);
                log.info("thumbnail 멤버컨트롤러에 있는 ! : " + userProfileDTOList);

            }
        }catch (Exception e){
            log.error("프로필 사진을 불러오는 중 오류 발생 : " + e.getMessage(), e);
        }
        return "myPage/mypage";
    }

    @PostMapping(value = "/updateInfo")
    public String modifyMember(@ModelAttribute MemberDTO memberDTO, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws MemberModifyException {
        System.out.println("파라미ㅓ 넘어왔니;???? : " + memberDTO);
        log.info("제발 나와라!!!!!!!!!!!!!!!! : ====modifyMember=====");

        memberDTO.setMemId(memberDTO.getMemId());

        String memAdrs = request.getParameter("memAdrs");
        memberDTO.setMemAdrs(memAdrs);

        String memAdrsDetail = request.getParameter("memAdrsDetail");
        memberDTO.setMemAdrsDetail(memAdrsDetail);

        String birthStr = request.getParameter("memBirth");
        System.out.println("birthStr : " + birthStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date memBirth = null;
        log.info("memBirth:" + memBirth);


        try {
            if(birthStr != null && !birthStr.isEmpty()){
                memBirth = dateFormat.parse(birthStr);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        log.info("memBirth:" + memBirth);
        memberDTO.setMemBirth(memBirth);

        loginService.modifyMember(memberDTO);

        SessionUtil.invalidateSession(request, response);

        rttr.addFlashAttribute("message", "회원 정보 수정에 성공하셨습니다.");

        log.info("[MemberController] modifyMember");

        return "/myPage/updateComplete";
    }

    @GetMapping("/pwdReset")
    public String modifyPwdForm(){
        return "myPage/pwdReset";
    }


    @PostMapping("/pwdReset")
    public String modifyPwd(@ModelAttribute MemberDTO memberDTO, @RequestParam("password") String memPwd , HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws MemberModifyException {

        log.info("pwdReset =======================" );

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int memCode = 0;

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            memCode = user.getMemCode();
            memberDTO.setMemCode(memCode);
        }
        log.info("memCode : " + memCode);


        if(memPwd != null){
            memberDTO.setMemPwd(passwordEncoder.encode(memPwd));
        }

        log.info("modifyPwd memberDTO: " + memberDTO);

        loginService.modifyPwd(memberDTO);

        SessionUtil.invalidateSession(request, response);

        rttr.addFlashAttribute("message", "비밀번호 수정에 성공하셨습니다. 다시 로그인해주세요😋 ");

        return "myPage/updateComplete";

    }


    @GetMapping("/delete")
    public String memberDeleteForm(){
        return "myPage/delete";
    }

    @PostMapping("/delete")
    public String memberDelete(@ModelAttribute MemberDTO memberDTO, @RequestParam("password") String memPwd, SessionStatus status,
                               HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws MemberModifyException {

        log.info("memberDelete=========================");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int memCode = 0;

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            memCode = user.getMemCode();
            memberDTO.setMemCode(memCode);

            String storedMemPwd = user.getMemPwd();

            if(passwordEncoder.matches(memPwd, storedMemPwd)){

                log.info("memCode : " + memCode);

                log.info("memberDTO : " + memberDTO);
                loginService.memberDelete(memberDTO);

                SessionUtil.invalidateSession(request, response);

                rttr.addFlashAttribute("message", "회원 탈퇴에 성공하셨습니다. 로그아웃 됩니다.");
            }else{
                rttr.addFlashAttribute("error", "현재 비밀번호가 일치하지 않습니다.");
            }
        }

        return "myPage/deleteComplete";

    }

}