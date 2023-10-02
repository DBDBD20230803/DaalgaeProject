package com.daalgae.daalgaeproject.mainConnect.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AllSearchPostDTO {
    private String post_Code;
    private String post_Type;
    private String post_Title;
    private String post_Writer;
    private String post_Count;
    private String post_Date;
}
