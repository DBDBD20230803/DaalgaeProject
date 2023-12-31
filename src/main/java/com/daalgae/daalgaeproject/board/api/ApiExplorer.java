package com.daalgae.daalgaeproject.board.api;

import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.daalgae.daalgaeproject.board.dto.CenterDTO;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiExplorer {
    public List<AbanInfoDTO> abanInfo(int pageNo, String city) throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=n2TVCKryQezP4TokLGOlzGKU7dA%2FnDt%2BclT6rTZ8mJ35FYxpzq6pFdOWfjjmdtqhu9dDX1JymgqA9emw2BwGLA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + URLEncoder.encode("20230801", "UTF-8")); /*유기날짜(검색 시작일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + URLEncoder.encode("20230925", "UTF-8")); /*유기날짜(검색 종료일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + URLEncoder.encode("417000", "UTF-8")); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900)*/
        urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조)*/

        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + URLEncoder.encode(city, "UTF-8")); /*시도코드 (시도 조회 OPEN API 참조)*/

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
            String chargeNm = item.isNull("chargeNm") ? null : item.getString("chargeNm");
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
            if(chargeNm != null) {
                abanInfoDTO.setChargeNm(chargeNm);
            }
            abanInfoDTO.setOfficetel(officetel);
            abanInfoDTO.setPopfile(popfile);
            abanInfoDTO.setTotalCount(totalCount); // 19

            abanInfoList.add(abanInfoDTO);
        }


        return abanInfoList;
    }

    public List<CenterDTO> centerInfoSearch(int pageNo, String centerName) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=n2TVCKryQezP4TokLGOlzGKU7dA%2FnDt%2BclT6rTZ8mJ35FYxpzq6pFdOWfjjmdtqhu9dDX1JymgqA9emw2BwGLA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호센터등록번호*/
        urlBuilder.append("&" + URLEncoder.encode("care_nm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*동물보호센터명*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8")); /*한 페이지 결과 수 (1,000 이하)*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/

        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8")); /*페이지 번호*/

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

        System.out.println("itemArray : " + itemArray);

        System.out.println("totalCount : " + totalCount);
        System.out.println("pageNo : " + pageNo);
        List<CenterDTO> centerInfoList = new ArrayList<>();

        for (int i = 0; i < itemArray.length(); i++) {

            JSONObject item = itemArray.getJSONObject(i);

            String careNm = item.isNull("careNm") ? null : item.getString("careNm");
            String careTel = item.isNull("careTel") ? null : item.getString("careTel");
            String orgNm = item.isNull("orgNm") ? null : item.getString("orgNm");
            String divisionNm = item.isNull("divisionNm") ? null : item.getString("divisionNm");
            String saveTrgtAnimal = item.isNull("saveTrgtAnimal") ? null : item.getString("saveTrgtAnimal");
            String careAddr = item.isNull("careAddr") ? null : item.getString("careAddr");
            String jibunAddr = item.isNull("jibunAddr") ? null : item.getString("jibunAddr");
            String dsignationDate = item.isNull("dsignationDate") ? null : item.getString("dsignationDate");
            String weekOprStime = item.isNull("weekOprStime") ? null : item.getString("weekOprStime");
            String weekOprEtime = item.isNull("weekOprEtime") ? null : item.getString("weekOprEtime");
            String weekCellStime = item.isNull("weekCellStime") ? null : item.getString("weekCellStime");
            String weekCellEtime = item.isNull("weekCellEtime") ? null : item.getString("weekCellEtime");
            String weekendOprStime = item.isNull("weekendOprStime") ? null : item.getString("weekendOprStime");
            String weekendOprEtime = item.isNull("weekendOprEtime") ? null : item.getString("weekendOprEtime");
            String weekendCellStime = item.isNull("weekendCellStime") ? null : item.getString("weekendCellStime");
            String weekendCellEtime = item.isNull("weekendCellEtime") ? null : item.getString("weekendCellEtime");
            String closeDay = item.isNull("closeDay") ? null : item.getString("closeDay");
            String dataStdDt = item.isNull("dataStdDt") ? null : item.getString("dataStdDt");
            Double lat = item.isNull("lat") ? null : item.getDouble("lat");
            Double lng = item.isNull("lng") ? null : item.getDouble("lng");
            Integer vetPersonCnt = item.optInt("vetPersonCnt", 0);
            Integer specsPersonCnt = item.optInt("specsPersonCnt", 0);
            Integer medicalCnt = item.optInt("medicalCnt", 0);
            Integer breedCnt = item.optInt("breedCnt", 0);
            Integer quarabtineCnt = item.optInt("quarabtineCnt", 0);
            Integer feedCnt = item.optInt("feedCnt", 0);
            Integer transCarCnt = item.optInt("transCarCnt", 0);

            CenterDTO centerInfoDTO = new CenterDTO();

            centerInfoDTO.setCareNm(careNm);
            centerInfoDTO.setCareTel(careTel);
            centerInfoDTO.setOrgNm(orgNm);
            centerInfoDTO.setDivisionNm(divisionNm);
            centerInfoDTO.setSaveTrgtAnimal(saveTrgtAnimal);
            centerInfoDTO.setCareAddr(careAddr);
            centerInfoDTO.setJibunAddr(jibunAddr);
            centerInfoDTO.setDsignationDate(dsignationDate);
            centerInfoDTO.setWeekOprStime(weekOprStime);
            centerInfoDTO.setWeekOprEtime(weekOprEtime);
            centerInfoDTO.setWeekCellStime(weekCellStime);
            centerInfoDTO.setWeekCellEtime(weekCellEtime);
            centerInfoDTO.setWeekendOprStime(weekendOprStime);
            centerInfoDTO.setWeekendOprEtime(weekendOprEtime);
            centerInfoDTO.setWeekendCellStime(weekendCellStime);
            centerInfoDTO.setWeekendCellEtime(weekendCellEtime);
            centerInfoDTO.setCloseDay(closeDay);
            centerInfoDTO.setDataStdDt(dataStdDt);
            centerInfoDTO.setLat(lat);
            centerInfoDTO.setLng(lng);
            centerInfoDTO.setVetPersonCnt(vetPersonCnt);
            centerInfoDTO.setSpecsPersonCnt(specsPersonCnt);
            centerInfoDTO.setMedicalCnt(medicalCnt);
            centerInfoDTO.setBreedCnt(breedCnt);
            centerInfoDTO.setQuarabtineCnt(quarabtineCnt);
            centerInfoDTO.setFeedCnt(feedCnt);
            centerInfoDTO.setTransCarCnt(transCarCnt);



            if (centerName == null || centerName.isEmpty()) {
                // "centerName"이 비어있을 때는 전체 리스트 반환
                centerInfoDTO.setTotalCount(totalCount);
                centerInfoList.add(centerInfoDTO);
            } else if (careNm.contains(centerName)) {
                // "centerName"이 비어있지 않을 때는 "careNm"에 포함된 부분 문자열 검색
                totalCount = centerInfoList.size();
                centerInfoDTO.setTotalCount(totalCount);
                centerInfoList.add(centerInfoDTO);
            }
        }

        return centerInfoList;
    }
}
