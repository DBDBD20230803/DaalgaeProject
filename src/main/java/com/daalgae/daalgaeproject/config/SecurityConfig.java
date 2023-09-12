package com.daalgae.daalgaeproject.config;

import com.daalgae.daalgaeproject.login.service.AuthenticationService;
import com.daalgae.daalgaeproject.login.service.LoginService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private LoginService loginService;



}
