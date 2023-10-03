package com.daalgae.daalgaeproject.admin.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDTO {

    private int reportCode;
    private String refReportMemCode;
    private String refReportDefanCode;
    private int refPostCode;
    private int refPostReplyCode;
    private String reportReason;
    private Date reportDate;
}
