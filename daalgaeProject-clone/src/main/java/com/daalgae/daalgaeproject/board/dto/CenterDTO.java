package com.daalgae.daalgaeproject.board.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CenterDTO {
    private String careNm; // 동물보호센터명
    private String careTel; // 전화번호
    private String orgNm; // 관리기관명
    private String divisionNm; // 동물보호센터유형
    private String saveTrgtAnimal; // 구조대상동물
    private String careAddr; // 소재지도로명주소
    private String jibunAddr; // 소재지번주소
    private String dsignationDate; // 동물보호센터지정일자
    private String weekOprStime; // 평일운영시작시각
    private String weekOprEtime; // 평일운영종료시각
    private String weekCellStime; // 평일분양시작시각
    private String weekCellEtime; // 평일분양종료시각
    private String weekendOprStime; // 주말운영시작시각
    private String weekendOprEtime; // 주말운영종료시각
    private String weekendCellStime; // 주말분양시작시각
    private String weekendCellEtime; // 주말분양종료시각
    private String closeDay; // 휴무일
    private String dataStdDt; // 데이터기준일자
    private Double lat; // 위도
    private Double lng; // 경도
    private Integer vetPersonCnt; // 수의사인원수
    private Integer specsPersonCnt; // 사양관리사인원수
    private Integer medicalCnt; // 진료실수
    private Integer breedCnt; // 사육실수
    private Integer quarabtineCnt; // 격리실수
    private Integer feedCnt; // 사료보관실수
    private Integer transCarCnt; // 구조운반용차량보유대수

    private int numOfRows; // 한 페이지 결과 수
    private int pageNo; // 페이지 번호
    private int totalCount; // 전체 결과수


}
