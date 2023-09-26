package com.daalgae.daalgaeproject.webtoon.service;

import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.webtoon.model.dto.DeductionRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.InsufficientResourcesException;

@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final MemberDAO memberDAO;

    @Transactional
    public int getMemberByMemCode(int memCode) {

        int member = memberDAO.getMemberByMemCode(memCode);

        return member;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean purchaseDogGum(Integer memDogGum) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserImpl){
                UserImpl user = (UserImpl) authentication.getPrincipal();
                int memCode = user.getMemCode();
                int currentMemDogGum = user.getMemDogGum();

                System.out.println("추가중... : " + memDogGum);
                int purchaseDogGum = currentMemDogGum - 2 ;
                int updateRows= memberDAO.purchaseDogGumus(memCode, purchaseDogGum);
                System.out.println("추가됨 ...22 : " + memDogGum);
                if (updateRows >= 1 ){
                    user.setMemDogGum(purchaseDogGum);
                    return  true;
                }else {
                    return false;
                }
            }
            return false;
        }catch (Exception e) {
            throw e;
        }
    }
}

