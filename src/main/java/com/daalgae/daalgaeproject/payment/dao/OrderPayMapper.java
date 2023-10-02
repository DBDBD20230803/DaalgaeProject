package com.daalgae.daalgaeproject.payment.dao;

import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import com.daalgae.daalgaeproject.payment.dto.UseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderPayMapper {


    int orderRegist(OrderPay orderPay);

    List<OrderPay> userSelect(int memCode);

    List<UseHistory> userAllFind(int dogGumUseCode);

    int insertDogGumUse(UseHistory useHistory);

    int purchaseGumus(@RequestParam("memCode") int memCode , @RequestParam("refPostCode") int refPostCode);

    boolean checkDuplicateDogGum(String dogGumUseDate);

    int duplicateDogGum (Map<String, Object> params);
}