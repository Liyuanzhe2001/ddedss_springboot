
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
public class GetEvaluationTimeRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

}
