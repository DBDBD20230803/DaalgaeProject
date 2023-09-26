package com.daalgae.daalgaeproject.board.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AbanInfoDTO {

//    private String reqNo;
//    private String resultCode;
//    private String resultMsg;
//    private String filename;
//    private String noticeEdt;

    private String desertionNo; // 유기번호 ㅠㅠ
    private String kindCd; // 품종
    private String sexCd; // 성별
    private String noticeNo; // 공고번호
    private String age; // 나이
    private String colorCd; // 색상
    private String weight; // 체중
    private String neuterYn; // 중성화여부
    private String processState; // 상태
    private String specialMark; // 특징
    private String happenDt; // 접수일
    private String noticeSdt; // 공고시작일
    private String happenPlace; // 발견장소
    private String careNm; // 보호소이름
    private String careTel; // 보호소연락처
    private String careAddr; // 보호소주소
    private String orgNm; // 관할기관
    private String chargeNm; // 담당자
    private String officetel; // 담당자연락처
    private String popfile; // 이미지
    private int totalCount; // 전체페이지수
    private int pageNo; // 현재페이지


}
