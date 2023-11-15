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
public class GetClassesByPageRespDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 班级名
     */
    private String  className;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 类型
     */
    private Short type;


}
