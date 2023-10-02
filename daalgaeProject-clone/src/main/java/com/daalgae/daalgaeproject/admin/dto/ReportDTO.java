package com.daalgae.daalgaeproject.admin.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDTO {

    private String refReportMemCode;
    private String refReportDefanCode;
    private int postCode;
    private int replyCode;
    private String reportReason;
}
