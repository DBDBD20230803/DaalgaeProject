package com.daalgae.daalgaeproject.webtoon.service;

import com.daalgae.daalgaeproject.board.dao.BoardMapper;
import com.daalgae.daalgaeproject.board.dto.BoardDTO;
import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
import com.daalgae.daalgaeproject.payment.dto.UseHistory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final MemberDAO memberDAO;
    private final OrderPayMapper orderPayMapper;
    private final BoardMapper boardMapper;

    @Transactional
    public int getMemberByMemCode(int memCode) {

        int member = memberDAO.getMemberByMemCode(memCode);

        return member;
    }

    @Transactional
    public int getPostCode(Integer postCode) {
        System.out.println("서비스 로직 : " + postCode);
        return boardMapper.dogGumUse(postCode);
    }

    public List<BoardDTO> userDogGum(int postCode) {

        return boardMapper.userDogGum(postCode);
    }

       /*@Transactional(rollbackFor = Exception.class)
        public boolean purchaseDogGum(Integer memDogGum) {
            try {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication.getPrincipal() instanceof UserImpl){
                    UserImpl user = (UserImpl) authentication.getPrincipal();

                    int memCode = user.getMemCode();
                    System.out.println("서비스 들렀다 갑니다. : " + memCode);
                    int purchaseDogGum = memDogGum - 2  ;
                    System.out.println("서비스 들렀다 갑니다. 22222 : " + purchaseDogGum);
                    int updateRows= memberDAO.purchaseDogGumus(memCode);
                    System.out.println("서비스 들렀다 갑니다. : 3333" + updateRows);
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
        }*/


  /*  @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String purchaseDogGum(String dogGumUseDate, int dogGumUseAmount, int refMemCode, Integer memDogGum) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserImpl) {
                UserImpl user = (UserImpl) authentication.getPrincipal();
                int memCode = user.getMemCode();

                boolean duplicateCount = orderPayMapper.checkDuplicateDogGum(dogGumUseDate);
                System.out.println("중복 검사 하고있니 ? : " + duplicateCount);

                if (duplicateCount) {
                    System.out.println(" 니 값이 뭔데 ? : " + duplicateCount);
                    return "duplicate";
                } else {
                    System.out.println();

                    UseHistory useHistory = new UseHistory();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(dogGumUseDate);
                    useHistory.setDogGumUseDate(parsedDate);
                    useHistory.setDogGumUseAmount(dogGumUseAmount);
                    useHistory.setRefMemCode(memCode);

                    int result = orderPayMapper.insertDogGumUse(useHistory);

                    if (result == 1) {
                        return "success";
                    } else {
                        return "error during saving";
                    }
                }
            } else {
                return "user not authenticated";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String purchaseDogGum(String dogGumUseDate, int dogGumUseAmount,  int refPostCode, int refMemCode, Integer memDogGum) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof UserImpl) {
                UserImpl user = (UserImpl) authentication.getPrincipal();
                int memCode = user.getMemCode();

                Map<String, Object> params = new HashMap<>();
                params.put("dogGumUseDate", refPostCode);
                System.out.println("postCode 로 : " + refPostCode);
                boolean duplicateCount = orderPayMapper.duplicateDogGum(params) > 1;
                System.out.println("파라미터값이 뭔디 : " + params);
                System.out.println("중복 검사 하고있니 ? : " + duplicateCount);

                if (duplicateCount) {
                    System.out.println(" 니 값이 뭔데 ? : " + duplicateCount);
                    return "duplicate";
                } else {
                    System.out.println("여기 아직 안들어옴 ? : " + duplicateCount ) ;

                    UseHistory useHistory = new UseHistory();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(dogGumUseDate);
                    useHistory.setDogGumUseDate(parsedDate);
                    useHistory.setDogGumUseAmount(refPostCode);
                    useHistory.setDogGumUseAmount(dogGumUseAmount);
                    useHistory.setRefMemCode(memCode);

                    int result = orderPayMapper.insertDogGumUse(useHistory);

                    System.out.println("insert 는 됐니 ? : " + result);

                    if (result == 1) {
                        return "success";
                    } else {
                        return "error during saving";
                    }
                }
            } else {
                return "user not authenticated";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


