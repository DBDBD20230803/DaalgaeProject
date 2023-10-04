package com.daalgae.daalgaeproject.matchingTest.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MatchingTestDTO {

    private int maltese;
    private int chiwawa;
    private int golden;
    private int hursky;
    private int shihtzu;
    private int mix;
    private int jindo;
    private int poodle;
    private int dogCode;
    private String dogKind;
    private String selectedKind;
    private String selectedName;
    private String testResult;

    public void setMaltese(int maltese) {
        this.maltese = maltese;
        this.dogKind = "말티즈";
    }

    public void setChiwawa(int chiwawa) {
        this.chiwawa = chiwawa;
        this.dogKind = "치와와";
    }

    public void setGolden(int golden) {
        this.golden = golden;
        this.dogKind = "골든 리트리버";
    }

    public void setHursky(int hursky) {
        this.hursky = hursky;
        this.dogKind = "허스키";
    }

    public void setShihtzu(int shihtzu) {
        this.shihtzu = shihtzu;
        this.dogKind = "시츄";
    }

    public void setMix(int mix) {
        this.mix = mix;
        this.dogKind = "믹스견";
    }

    public void setJindo(int jindo) {
        this.jindo = jindo;
        this.dogKind = "진도견";
    }

    public void setPoodle(int poodle) {
        this.poodle = poodle;
        this.dogKind = "푸들";
    }

}
