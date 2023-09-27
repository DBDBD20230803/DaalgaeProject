package com.daalgae.daalgaeproject.webtoon.service;

import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final MemberDAO memberDAO;
    private final OrderPayMapper orderPayMapper;

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

                int purchaseDogGum = memDogGum - 2  ;

                int updateRows= memberDAO.purchaseDogGumus(memCode, purchaseDogGum);

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


    public Date getPayDate(Date payDate) {
        return null;
    }
}

