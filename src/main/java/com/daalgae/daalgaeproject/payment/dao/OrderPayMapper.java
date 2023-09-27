package com.daalgae.daalgaeproject.payment.dao;

import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderPayMapper {


    int orderRegist(OrderPay orderPay);

    List<OrderPay> userSelect(int memCode);


    int hasReadPost(@Param("memCode") int memCode, @Param("postCode") int postCode);


}
