package com.daalgae.daalgaeproject.board.api;

import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;

import java.util.List;
import java.util.Optional;

public class AbanInfoByDesertionNo {

    public AbanInfoByDesertionNo() {
    }

    public AbanInfoDTO getAbanInfoByDesertionNo(List<AbanInfoDTO> abanInfoList, String desiredDesertionNo) {
        Optional<AbanInfoDTO> optionalAbanInfo = abanInfoList.stream()
                .filter(abanInfo -> abanInfo.getDesertionNo().equals(desiredDesertionNo))
                .findFirst();

        if (optionalAbanInfo.isPresent()) {
            return optionalAbanInfo.get();
        } else {
            // 해당 desertionNo를 찾을 수 없음. 예외 처리 또는 기본값 반환
            return null; // 또는 다른 방식으로 처리
        }
    }
}