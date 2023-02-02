package com.lyz.ddedss_springboot.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GetExaminationResultsRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目名称
     */
    private String  subjectName;

    /**
     * 优秀个数 （80-100）
     */
    private Integer good;

    /**
     * 中等个数 （60-80）
     */
    private Integer mid;

    /**
     * 差个数（60- ）
     */
    private Integer bad;

}
