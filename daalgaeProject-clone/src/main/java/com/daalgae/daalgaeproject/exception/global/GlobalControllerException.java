package com.daalgae.daalgaeproject.exception.global;

import com.daalgae.daalgaeproject.exception.member.MemberRegistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerException {

    @ExceptionHandler(MemberRegistException.class)
    public String memberRegistExceptionHandler(Model model, MemberRegistException e){

        model.addAttribute("exception", "regist | " + e.getMessage());
        System.out.println("Regist Exception = " + "공지사항 등록 실패");
        return  "regist/regist";
    }
}
