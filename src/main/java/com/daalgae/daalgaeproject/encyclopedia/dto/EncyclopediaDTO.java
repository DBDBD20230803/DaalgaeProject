package com.daalgae.daalgaeproject.encyclopedia.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EncyclopediaDTO {
    private int encycleCode;
    private String encycleOrder;
    private String encycleImage;
    private String encycleTitle;
    private String encycleContent;
}
