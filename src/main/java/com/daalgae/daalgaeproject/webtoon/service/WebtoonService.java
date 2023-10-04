package com.daalgae.daalgaeproject.webtoon.service;

import com.daalgae.daalgaeproject.board.dao.BoardMapper;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
import com.daalgae.daalgaeproject.webtoon.model.dto.UseHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final MemberDAO memberDAO;
    private final OrderPayMapper orderPayMapper;
    private final BoardMapper board;

    @Transactional
    public int getMemberByMemCode(int memCode) {

        int member = memberDAO.getMemberByMemCode(memCode);

        return member;
    }

    @Transactional
    public int getPostCode(Integer postCode) {
        System.out.println("서비스 로직 : " + postCode);
//        return boardMapper.dogGumUse(postCode);
        return 0;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String purchaseDogGum(String dogGumUseDate, int dogGumUseAmount, Integer memDogGum) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserImpl) {
                UserImpl user = (UserImpl) authentication.getPrincipal();
                int memCode = user.getMemCode();
                int postCode = 0;
                Integer refPostCode = orderPayMapper.getPostCodeBySomeCriteria(postCode);

                // 중복 확인 쿼리 실행
                int duplicateCount = (refPostCode != null) ? orderPayMapper.duplicateDogGum(refPostCode) : 0;

                // refPostCode가 null이면 기본값으로 설정 (예: -1)
                if (refPostCode == null) {
                    refPostCode = -1;
                } else {
                    // 개껌 사용 이력 저장
                    UseHistory useHistory = new UseHistory();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    Date parsedDate = dateFormat.parse(dogGumUseDate);
                    useHistory.setDogGumUseDate(parsedDate);
                    useHistory.setDogGumUseAmount(dogGumUseAmount);
                    useHistory.setRefMemCode(memCode);
                    // refPostCode 설정
                    useHistory.setRefPostCode(refPostCode);

                    int result = orderPayMapper.insertDogGumUse(useHistory);
                    if (result == 1) {
                        return "success"; // 구매 성공
                    } else {
                        return "error during saving"; // 저장 중 오류
                    }
                }

                if (duplicateCount > 0) {
                    return "duplicate"; // 중복 구매
                } else {
                    // 구매 처리
                    int updateRows = orderPayMapper.purchaseGums(memCode);
                    if (updateRows >= 1) {
                        // 구매 성공 처리
                        int purchaseDogGum = memDogGum - 2;
                        user.setMemDogGum(purchaseDogGum);
                        return "success"; // 구매 성공
                    } else {
                        return "error during purchase"; // 구매 중 오류
                    }
                }
            } else {
                return "user not authenticated"; // 사용자 인증 실패
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}




