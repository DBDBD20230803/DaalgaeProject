package com.daalgae.daalgaeproject.payment.dao;

import com.daalgae.daalgaeproject.board.dao.BoardMapper;
import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import com.daalgae.daalgaeproject.webtoon.model.dto.UseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderPayMapper {


    int orderRegist(OrderPay orderPay);

    List<OrderPay> userSelect(int memCode);

    List<UseHistory> userAllFind(int dogGumUseCode);

    int purchaseDogGumus (@Param("memCode") int memCode, @Param("memDogGum") int memDogGum);

    int insertDogGumUse(UseHistory useHistory);

    int boardSelect (BoardMapper board);
    int purchaseGums( int memCode);

    int duplicateDogGum(int refpostCode);

   Integer getPostCodeBySomeCriteria( int postCode);
}