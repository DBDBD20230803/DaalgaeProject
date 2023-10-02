package com.daalgae.daalgaeproject.member.controller;

import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private MemberDAO memberDAO;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage;
        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "서버에서 오류가 발생하였습니다. \n 관리자에게 문의해주세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 회원입니다. \n 아이디를 확인해주세요.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            log.info("이메일이 인증되지 않았습니다. 이메일을 확인해 주세요.");
            errorMessage = "인증 요청이 거부되었습니다. \n 관리자에게 문의해주세요.";
        }

            else {
                errorMessage = "제제당하거나 탈퇴한 회원은 로그인되지 않습니다.";
            }


            errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
            setDefaultFailureUrl("/login/loginFail?errorMessage=" + errorMessage);
            super.onAuthenticationFailure(request, response, exception);
        }
    }

