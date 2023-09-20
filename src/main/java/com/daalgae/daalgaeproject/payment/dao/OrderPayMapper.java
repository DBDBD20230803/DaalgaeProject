package com.daalgae.daalgaeproject.payment.dao;

import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderPayMapper {

    int orderRegist(OrderPay orderPay);

    List<OrderPay> findAllPay();
}
