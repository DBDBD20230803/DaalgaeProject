package com.daalgae.daalgaeproject.matchingTest.model.service;

import com.daalgae.daalgaeproject.matchingTest.model.dao.MatchingTestDAO;
import com.daalgae.daalgaeproject.matchingTest.model.dto.MatchingTestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MatchingTestService {

    private final MatchingTestDAO matchingTestDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public MatchingTestService(MatchingTestDAO matchingTestDAO) {
        this.matchingTestDAO = matchingTestDAO;
    }

    public void testResult(MatchingTestDTO matchingTestDTO) {

        String selectedKind = "";
        String selectedName = "";

        int maxCount = -1; // 최대 `value`값 초기화

        // 각 견종의 값을 비교하여 최대 `value`값을 찾고 해당 견종의 이름 설정
        int maltese = matchingTestDTO.getMaltese();
        if (maltese > maxCount) {
            maxCount = maltese;
            selectedName = "말티즈";
        }

        int chiwawa = matchingTestDTO.getChiwawa();
        if (chiwawa > maxCount) {
            maxCount = chiwawa;
            selectedName = "치와와";
        }

        int golden = matchingTestDTO.getGolden();
        if (golden > maxCount) {
            maxCount = golden;
            selectedName = "골든 리트리버";
        }

        int hursky = matchingTestDTO.getHursky();
        if (hursky > maxCount) {
            maxCount = hursky;
            selectedName = "허스키";
        }

        int shihtzu = matchingTestDTO.getShihtzu();
        if (shihtzu > maxCount) {
            maxCount = shihtzu;
            selectedName = "시츄";
        }

        int mix = matchingTestDTO.getMix();
        if (mix > maxCount) {
            maxCount = mix;
            selectedName = "믹스견";
        }

        int jindo = matchingTestDTO.getJindo();
        if (jindo > maxCount) {
            maxCount = jindo;
            selectedName = "진도견";
        }

        int poodle = matchingTestDTO.getPoodle();
        if (poodle > maxCount) {
            maxCount = poodle;
            selectedName = "푸들";
        }

        // 선택된 견종들을 로그에 출력
        log.info("선택된 견종 이름: " + selectedName);

        // 최대 `value`값을 `selectedKind`에 설정
        selectedKind = String.valueOf(maxCount);
        log.info("selectedKind : " + selectedKind);

        matchingTestDTO.setDogKind(selectedName);
        matchingTestDTO.setSelectedKind(selectedKind);
        matchingTestDTO.setSelectedName(selectedName);
        matchingTestDAO.testResult(matchingTestDTO);
        log.info("selectedKind : " + selectedKind);
        log.info("====매칭테스트 서비스====받아와라!: " + matchingTestDTO);
    }


     /*   String selectedKind = "";
        String selectedName = "";


        int maltese = matchingTestDTO.getMaltese();
        log.info(String.valueOf(maltese));
        if(maltese >= 2){
            selectedKind = String.valueOf(maltese);
            selectedName = "말티즈";
        }

        int chiwawa = matchingTestDTO.getChiwawa();
        log.info(String.valueOf(chiwawa));
        if (chiwawa >= 2) {
            selectedKind = String.valueOf(chiwawa);
            selectedName = "치와와";
        }

        int golden = matchingTestDTO.getGolden();
        log.info(String.valueOf(golden));
        if (golden >= 2) {
            selectedKind = String.valueOf(golden);
            selectedName = "골든 리트리버";
        }

        int hursky = matchingTestDTO.getHursky();
        log.info(String.valueOf(hursky));
        if (hursky >= 2) {
            selectedKind = String.valueOf(hursky);
            selectedName = "허스키";
        }

        int shihtzu = matchingTestDTO.getShihtzu();
        log.info(String.valueOf(shihtzu));
        if (shihtzu >= 2) {
            selectedKind = String.valueOf(shihtzu);
            selectedName = "시츄";
        }

        int mix = matchingTestDTO.getMix();
        log.info(String.valueOf(mix));
        if (mix >= 2) {
            selectedKind = String.valueOf(mix);
            selectedName = "믹스견";
        }

        int jindo = matchingTestDTO.getJindo();
        log.info(String.valueOf(jindo));
        if (jindo >= 2) {
            selectedKind = String.valueOf(jindo);
            selectedName = "진도견";
        }

        int poodle = matchingTestDTO.getPoodle();
        log.info(String.valueOf(poodle));
        if (poodle >= 2) {
            selectedKind = String.valueOf(poodle);
            selectedName = "푸들";
        }



        if (selectedKind.isEmpty() && selectedName.isEmpty()) {
            selectedKind = String.valueOf(shihtzu);
            selectedName = "시츄";
        }

        // 선택된 견종들을 로그에 출력
        log.info("선택된 견종갯수: " + selectedKind);
        log.info("선택된 견종이름: " + selectedName);

        matchingTestDTO.setDogKind(selectedName);
        matchingTestDTO.setSelectedKind(selectedKind);
        log.info("selectedKind: " + selectedKind);
        matchingTestDTO.setSelectedName(selectedName);
        matchingTestDAO.testResult(selectedName);
        log.info("====매칭테스트 서비스====받아와라!: " + matchingTestDTO);
 */

    public List<MatchingTestDTO> getResult(MatchingTestDTO matchingTestDTO) {
        log.info("MatchingTestService: " + matchingTestDTO);
        return matchingTestDAO.getResult(matchingTestDTO);


    }
}
