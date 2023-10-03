package com.daalgae.daalgaeproject.payment.dao;

import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import com.daalgae.daalgaeproject.webtoon.model.dto.UseHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderPayMapper {


    int orderRegist(OrderPay orderPay);

    List<OrderPay> userSelect(int memCode);

    List<UseHistory> userAllFind(int dogGumUseCode);

    int insertDogGumUse(UseHistory useHistory);

    int purchaseGumus(int memCode);

    int duplicateDogGum(String refPostCode);
}