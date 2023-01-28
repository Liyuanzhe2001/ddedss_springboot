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
public class GetAvgScoreByExamIdRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平均分
     */
    private Double avgScore;

}
