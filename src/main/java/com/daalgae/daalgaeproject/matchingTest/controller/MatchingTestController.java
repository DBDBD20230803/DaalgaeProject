package com.daalgae.daalgaeproject.matchingTest.controller;

import com.daalgae.daalgaeproject.matchingTest.model.dto.MatchingTestDTO;
import com.daalgae.daalgaeproject.matchingTest.model.service.MatchingTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matchingTest")
public class MatchingTestController {
    private final MatchingTestService matchingTestService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public MatchingTestController(MatchingTestService matchingTestService) {
        this.matchingTestService = matchingTestService;
    }

    @GetMapping("/matchingTestMain")
    public String matchingTestMainForm(){ return "matchingTest/matchingTestMain"; }

    @GetMapping("/matchingTest")
    public String matchingTestForm(){
        return "matchingTest/test1";
    }

    @PostMapping("/result")
    public ResponseEntity<String> matchingTestResult(@RequestBody MatchingTestDTO matchingTestDTO, Model model){

        try{
            log.info("매칭테스트===========================" + matchingTestDTO);

            matchingTestService.testResult(matchingTestDTO);
            return ResponseEntity.ok("매칭 테스트 결과가 성공적으로 저장되었습니다.");
        }catch (Exception e){
            log.error("매칭 테스트 결과 저장 중 오류 발생 : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("매칭 테스트 결과 저장 중 오류 발생했습니다.");
        }

    }
}
