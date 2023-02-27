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
public class GetFiveYearResultRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 近五年科目平均分，若无成绩，则返回0
     * 年 平均分
     */
    private String[] resultData;

}
