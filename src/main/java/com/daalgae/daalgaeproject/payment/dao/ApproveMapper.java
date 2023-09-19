package com.daalgae.daalgaeproject.payment.dao;

import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import org.apache.ibatis.annotations.Mapper;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApproveMapper {


    int registKakaoPay(KakaoApprove kakaoApprove);
}
