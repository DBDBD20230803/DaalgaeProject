package com.daalgae.daalgaeproject.matchingTest.model.service;

import com.daalgae.daalgaeproject.matchingTest.model.dao.MatchingTestDAO;
import com.daalgae.daalgaeproject.matchingTest.model.dto.MatchingTestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingTestService {
    private final MatchingTestDAO matchingTestDAO;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public MatchingTestService(MatchingTestDAO matchingTestDAO) {
        this.matchingTestDAO = matchingTestDAO;
    }

    public void testResult(MatchingTestDTO matchingTestDTO) {
        String selectedKind = null;

      // 각 견종의 값을 비교하여 2 이상인 값을 리스트에 추가
       /* if (matchingTestDTO.getMaltese() >= 2) {
            selectedKind = "말티즈";
        }*/

        int maltese = matchingTestDTO.getMaltese();
        log.info(String.valueOf(maltese));
        if(maltese >= 2){
            selectedKind = String.valueOf(maltese);
        }

        int chiwawa = matchingTestDTO.getChiwawa();
        log.info(String.valueOf(chiwawa));
        if (chiwawa >= 2) {
            selectedKind = String.valueOf(chiwawa);
        }

        int golden = matchingTestDTO.getGolden();
        log.info(String.valueOf(golden));
        if (golden >= 2) {
            selectedKind = String.valueOf(golden);
        }

        int hursky = matchingTestDTO.getHursky();
        log.info(String.valueOf(hursky));
        if (hursky >= 2) {
            selectedKind = String.valueOf(hursky);
        }

        int shihtzu = matchingTestDTO.getShihtzu();
        log.info(String.valueOf(shihtzu));
        if (shihtzu >= 2) {
            selectedKind = String.valueOf(shihtzu);
        }

        int mix = matchingTestDTO.getMix();
        log.info(String.valueOf(mix));
        if (mix >= 2) {
            selectedKind = String.valueOf(mix);
        }

        int jindo = matchingTestDTO.getJindo();
        log.info(String.valueOf(jindo));
        if (jindo >= 2) {
            selectedKind = String.valueOf(jindo);
        }

        int poodle = matchingTestDTO.getPoodle();
        log.info(String.valueOf(poodle));
        if (poodle >= 2) {
            selectedKind = String.valueOf(poodle);
        }



        if (selectedKind.isEmpty()) {
            selectedKind = String.valueOf(shihtzu);
        }

        // 선택된 견종들을 로그에 출력
        log.info("선택된 견종: " + selectedKind);

        matchingTestDTO.setSelectedKind(selectedKind);
        matchingTestDAO.testResult(selectedKind);
        log.info("====매칭테스트 서비스====받아와라!: " + matchingTestDTO);
    }

    public List<MatchingTestDTO> getResult(String selectedKind) {
        log.info("MatchingTestService: " + selectedKind);
        return matchingTestDAO.getResult(selectedKind);


    }
}
