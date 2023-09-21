
package com.daalgae.daalgaeproject.config;

import com.daalgae.daalgaeproject.member.controller.AuthFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthFailHandler authFailHandler;
    public SecurityConfig(AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }




    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/imgaes/**", "/lib/**");
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/*").authenticated()
                .antMatchers(HttpMethod.GET, "/matchginTest/*", "/webtoon/*","/daalgaeEncyclopedia/*").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/board/*", "/myPage/*", "/login/*").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login/login")
                .failureHandler(authFailHandler)
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                //.failureUrl("/login/login")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/header/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/common/denied")
                .and().build();

    }



}

