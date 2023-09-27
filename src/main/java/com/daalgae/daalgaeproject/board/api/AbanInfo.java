package com.daalgae.daalgaeproject.board.api;

import com.daalgae.daalgaeproject.board.dto.AbanInfoDTO;

import java.util.List;
import java.util.Optional;

public class AbanInfo {

    public AbanInfo() {
    }
    public AbanInfoDTO getAbanInfoByDesertionNo(List<AbanInfoDTO> abanInfoList, String desiredDesertionNo) {
        Optional<AbanInfoDTO> optionalAbanInfo = abanInfoList.stream()
                .filter(abanInfo -> abanInfo.getDesertionNo().equals(desiredDesertionNo))
                .findFirst();
        if (optionalAbanInfo.isPresent()) {
            return optionalAbanInfo.get();
        } else {
            return null;
        }
    }

}