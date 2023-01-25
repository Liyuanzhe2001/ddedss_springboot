package com.lyz.ddedss_springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SubjectLevelVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 科目
     */
    private String subjectName;


    /**
     * 熟悉度
     */
    private Short level;
}
