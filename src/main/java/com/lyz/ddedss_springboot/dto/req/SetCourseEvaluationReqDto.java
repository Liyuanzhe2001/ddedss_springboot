package com.lyz.ddedss_springboot.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SetCourseEvaluationReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评价开始时间
     */
    private String start;

    /**
     * 评价结束时间
     */
    private String end;

}
