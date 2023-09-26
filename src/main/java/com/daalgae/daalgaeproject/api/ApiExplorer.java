package com.daalgae.daalgaeproject.api;

import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiExplorer {
    public List<AbanInfoDTO> abanInfo(int pageNo) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=n2TVCKryQezP4TokLGOlzGKU7dA%2FnDt%2BclT6rTZ8mJ35FYxpzq6pFdOWfjjmdtqhu9dDX1JymgqA9emw2BwGLA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode("20230801", "UTF-8")); /*유기날짜(검색 시작일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode("20230925", "UTF-8")); /*유기날짜(검색 종료일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900)*/
        urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*상태(전체 : null(빈값), 공고중 : notice, 보호중 : protect)*/
        urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*상태 (전체 : null(빈값), 예 : Y, 아니오 : N, 미상 : U)*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("9", "UTF-8")); /*페이지당 보여줄 개수 (1,000 이하), 얘도 고정임 */

        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
        /*페이지 번호 (기본값 : 1) paging이랑 엮어서 돌려야함. thymeleaf 혹은 url에서 currentPage 값을 들고와서 입력해주면됨 그럼 자동 페이징이 됨. */

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String result = sb.toString();

        JSONObject jsonObject = new JSONObject(result);

        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = (JSONObject)response.get("body");
        JSONObject items = (JSONObject)body.get("items");

        int totalCount = body.getInt("totalCount");
        JSONArray itemArray = (JSONArray)items.get("item");

        System.out.println("totalCount : " + totalCount);
        System.out.println("pageNo : " + pageNo);
        List<AbanInfoDTO> abanInfoList = new ArrayList<>();

        for (int i = 0; i < itemArray.length(); i++) {

            JSONObject item = itemArray.getJSONObject(i);

            String desertionNo = item.getString("desertionNo");
            String kindCd = item.getString("kindCd");
            String sexCd = item.getString("sexCd");
            String noticeNo = item.getString("noticeNo");
            String age = item.getString("age");
            String colorCd = item.getString("colorCd");
            String weight = item.getString("weight");
            String neuterYn = item.getString("neuterYn");
            String processState = item.getString("processState");
            String specialMark = item.getString("specialMark");
            String happenDt = item.getString("happenDt");
            String noticeSdt = item.getString("noticeSdt");
            String happenPlace = item.getString("happenPlace");
            String careNm = item.getString("careNm");
            String careTel = item.getString("careTel");
            String careAddr = item.getString("careAddr");
            String orgNm = item.getString("orgNm");
            String chargeNm = item.getString("chargeNm");
            String officetel = item.getString("officetel");
            String popfile = item.getString("popfile");

            AbanInfoDTO abanInfoDTO = new AbanInfoDTO();

            abanInfoDTO.setDesertionNo(desertionNo);
            abanInfoDTO.setKindCd(kindCd);
            abanInfoDTO.setSexCd(sexCd);
            abanInfoDTO.setNoticeNo(noticeNo);
            abanInfoDTO.setAge(age);
            abanInfoDTO.setColorCd(colorCd);
            abanInfoDTO.setWeight(weight);
            abanInfoDTO.setNeuterYn(neuterYn);
            abanInfoDTO.setProcessState(processState);
            abanInfoDTO.setSpecialMark(specialMark);
            abanInfoDTO.setHappenDt(happenDt);
            abanInfoDTO.setNoticeSdt(noticeSdt);
            abanInfoDTO.setHappenPlace(happenPlace);
            abanInfoDTO.setCareNm(careNm);
            abanInfoDTO.setCareTel(careTel);
            abanInfoDTO.setCareAddr(careAddr);
            abanInfoDTO.setOrgNm(orgNm);
            abanInfoDTO.setChargeNm(chargeNm);
            abanInfoDTO.setOfficetel(officetel);
            abanInfoDTO.setPopfile(popfile);
            abanInfoDTO.setTotalCount(totalCount); // 19

            abanInfoList.add(abanInfoDTO);
        }


        return abanInfoList;
    }
}
