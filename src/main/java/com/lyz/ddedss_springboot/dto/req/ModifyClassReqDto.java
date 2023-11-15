package com.lyz.ddedss_springboot.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ModifyClassReqDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    private String id;

    /**
     * 班级名
     */
    private Integer name;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 类型
     */
    private Short type;

    /**
     * 专业
     */
    private String major;

}
