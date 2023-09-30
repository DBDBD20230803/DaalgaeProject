package com.daalgae.daalgaeproject.userProfile.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    private int profileCode;
    private String profileOriginName;
    private String profileDbName;
    private String profileThumbAddr;
    private String profileOriginAddr;
    private int refMemCode;
    private List<UserProfileDTO> userProfileList;

    public List<UserProfileDTO> setUserProfileList(ArrayList<UserProfileDTO> userProfileDTOS) {
        return this.userProfileList = userProfileList;
    }

    public List<UserProfileDTO> getUserProfileList() {
        return this.userProfileList = userProfileList ;
    }
}
